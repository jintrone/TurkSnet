package edu.mit.cci.turksnet.util;


import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.plugins.SessionCreationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * User: jintrone
 * Date: 12/8/11
 * Time: 9:28 PM
 */
public class WaitingRoomManager {

    private Experiment ex;
    private Plugin p;
    private boolean force = false;

    private final static WorkerQueue checkins = new MemoryBasedQueue();

    public WaitingRoomManager(Experiment e) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.ex = e;
        this.p = e.getActualPlugin();
        final Timer t = new Timer();
        t.schedule(getTimerTask(t), 1000l);


    }

    public void setForce(boolean b) {
        this.force = b;
    }

    public boolean getForce() {
        return force;
    }


    public TimerTask getTimerTask(final Timer t) {
        return new TimerTask() {
            @Transactional
            public void run() {


                try {

                    Session_ s = assignSession();
                    if (s != null) {
                        s.run();
                    }
                    t.schedule(getTimerTask(t), 1000);



                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (Exception e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        };
    }

    public Map<String, Object> checkin(Worker w) {
        return checkin(w.getId());
    }

    public Map<String, Object> checkin(long w) {
        //Worker.entityManager().refresh(w,LockModeType.PESSIMISTIC_WRITE);
        checkins.checkin(w, false);

        int node_count =getDesiredNumberOfUsers();
        int available = checkins.countAvailable(10000l, false);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("percent", "" + (float) available / node_count);
        result.put("workers", available);
        return result;
    }

    public int getWaiting(boolean prune) {
        return checkins.countAvailable(10000l, prune);
    }

    private int getDesiredNumberOfUsers() {

        int node_count = Integer.MAX_VALUE;
        if (ex.getProperty(Plugin.PROP_NODE_COUNT)!=null) {
            node_count = Integer.parseInt(ex.getProperty(Plugin.PROP_NODE_COUNT));
        }
        return node_count;
    }


    public Session_ assignSession() throws SessionCreationException {
        Session_ result = null;
        if ((checkins.countAvailable(10000l, true) >= getDesiredNumberOfUsers()) || force) {
            List<Worker> available = new ArrayList<Worker>(checkins.getAvailable(10000l, true));
            ex = Experiment.findExperiment(ex.getId());
            result = p.createSession(ex, available, force);
            if (result != null) {
                if (force) {
                    force = false;
                }
                available.clear();
                for (Node n:result.getNodesAsList()) {
                    available.add(n.getWorker());
                }
                checkins.remove(available);
            }
        }


        return result;
    }


}
