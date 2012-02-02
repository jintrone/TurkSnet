package edu.mit.cci.turksnet.util;


import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.plugins.SessionCreationException;
import org.springframework.transaction.annotation.Transactional;

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

    private final Experiment ex;
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
                    } else {
                        t.schedule(getTimerTask(t), 1000);
                    }


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
        //Worker.entityManager().refresh(w,LockModeType.PESSIMISTIC_WRITE);
        checkins.checkin(w, false);
        Map<String, String> props = ex.getPropsAsMap();
        int node_count = Integer.MAX_VALUE;
        if (props.get(Plugin.PROP_NODE_COUNT) != null) {
            node_count = Integer.parseInt(props.get(Plugin.PROP_NODE_COUNT));
        }

        int available = checkins.countAvailable(10000l, false);


        Map<String, Object> result = new HashMap<String, Object>();
        result.put("percent", "" + (float) available / node_count);
        result.put("workers", available);
        return result;
    }

    public int getWaiting(boolean prune) {
        return checkins.countAvailable(10000l, prune);
    }


    public Session_ assignSession() throws SessionCreationException {
        Session_ result;

        List<Worker> available = checkins.getAvailable(10000l, true);
        result = p.createSession(ex, available, force);
        if (result != null) {
            if (force) {
                force = false;
            }
            checkins.remove(available);
        }


        return result;
    }


}
