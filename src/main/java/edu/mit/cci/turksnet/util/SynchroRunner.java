package edu.mit.cci.turksnet.util;

import edu.mit.cci.turkit.util.TurkitOutputSink;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * User: jintrone
 * Date: 10/27/11
 * Time: 5:21 PM
 */
public class SynchroRunner implements RunStrategy {

    private Session_ session;
    private TurkitOutputSink sink;

    private GameState gameState;


    private static Logger log = Logger.getLogger(SynchroRunner.class);


    private Set<Long> queue = Collections.synchronizedSet(new HashSet<Long>());

    private final WorkerQueue wq = new MemoryBasedQueue();

    private static long TIMEOUT = 10000l;

    private long turnEnd;

    private long turnLength;


    @Override
    public void init(Session_ session, TurkitOutputSink sink) {
        this.session = session;
        this.sink = sink;

    }

    @Transactional
    private void startTurn() {
        log.debug("Start turn with " + turnLength + " sec remaining");
        //TODO this is to workaround merge failing because we're out of synch.  I don't think this is bulletproof - do i
        //TODO to synchronize?  How can a reattach and update?
        session = Session_.findSession_(session.getId());

        gameState = GameState.IN_TURN;
        turnEnd = System.currentTimeMillis() + turnLength;
        session.setIteration(session.getIteration() + 1);
        for (Node n : session.getAvailableNodes()) {
            queue.add(n.getId());
        }


    }

    /**
     * Contains the execution logic for the SynchroRunner
     *
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Transactional
    private boolean advanceState() throws ClassNotFoundException, InstantiationException, IllegalAccessException, JSONException {

        log.debug("Current state: " + gameState);
        if (gameState == GameState.IN_TURN) {

            //uncomment to support submissions from users
//            if (queue.isEmpty()) {
//                gameState = GameState.DONE_TURN;
//                advanceState();
//            } else
            if (System.currentTimeMillis() > turnEnd) {
                gameState = GameState.WAITING_FOR_RESULTS;

            }


        } else if (gameState == GameState.WAITING_FOR_RESULTS) {
            if (queue.isEmpty()) {
                gameState = GameState.DONE_TURN;
            } else {
                for (Long n : new HashSet<Long>(queue)) {
                    Node node = Node.findNode(n);
                    if (!wq.isActive(node.getWorker().getId(), TIMEOUT)) {
                        session.logNodeEvent(node, "timeout");
                        session.getExperiment().getActualPlugin().automateNodeTurn(node);
                    }
                }
            }

        } else if (gameState == GameState.DONE_TURN || gameState == null) {
            if (session.getExperiment().getActualPlugin().checkDone(session)) {
                gameState = GameState.DONE_GAME;
            } else {

                startTurn();
            }
        } else if (gameState == GameState.DONE_GAME) {
            log.info("Completing session "+session.getId());
            cleanupSession(Session_.Status.COMPLETE);
            return false;
        } else if (gameState == GameState.FORCE_DONE_GAME) {
            cleanupSession(Session_.Status.ABORTED);
            return false;
        }
        return true;

    }

    @Transactional
    private void cleanupSession(Session_.Status status) {
        session = Session_.findSession_(session.getId());
        session.setStatus(status);
        session.flush();
        for (Node n:session.getNodesAsList()) {
            finalizeTurnForWorker(n.getWorker());
        }
        wq.clear();
    }

    @Override
    public void run(boolean repeat) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        turnLength = 1000l * Integer.parseInt(session.getProperty(Plugin.PROP_TURN_LENGTH_SECONDS));
        final Timer t = new Timer(true);
        t.schedule(getTimerTask(t), 1000l);
    }

    //will update, driven by timer thread
    private TimerTask getTimerTask(final Timer t) {
        return new TimerTask() {


            public void run() {
                try {
                    if (advanceState()) {

                        t.schedule(getTimerTask(t), 1000l);
                    } else {

                        t.cancel();
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        };
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void haltOnNext() {
        gameState = GameState.FORCE_DONE_GAME;
    }

    @Override
    public boolean isRunning() {
        return (gameState == null ? 0 : gameState.ordinal()) < GameState.DONE_GAME.ordinal();
    }


    //will update node  - request driven
    @Override
    @Transactional
    public void updateNode(Node node, String results) throws ClassNotFoundException, IllegalAccessException, InstantiationException, JSONException {
        log.debug("Process results; queue length " + queue.size());
        node.getSession_().getExperiment().getActualPlugin().processResults(node, results);
        node.getSession_().logNodeEvent(node, "results");
        queue.remove(node.getId());
        log.debug("Queue after processing: " + queue.size());
    }

    //will update worker - request driven
    @Override
    public Map<String, Object> ping(long workerid) {
        wq.checkin(workerid, false);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", gameState);

        if (gameState == GameState.IN_TURN) {
            result.put("turn", this.session.getIteration() + "");
            result.put("remaining", Math.max(0, turnEnd - System.currentTimeMillis()));
        } else if (gameState == GameState.DONE_GAME) {
            log.debug("Finalizing turn for worker");
            finalizeTurnForWorker(Worker.findWorker(workerid));


        }
        return result;

    }

    @Transactional
    private void finalizeTurnForWorker(Worker w) {
        Worker.entityManager().refresh(w, LockModeType.PESSIMISTIC_WRITE);
        w.setCurrentAssignment(null);
        w.flush();


    }


}
