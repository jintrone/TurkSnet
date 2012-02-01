package edu.mit.cci.turksnet.util;


import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.plugins.SessionCreationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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

    private final static LinkedHashMap<Long, Long> checkins = new LinkedHashMap<Long, Long>();

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
        Map<String, String> props = ex.getPropsAsMap();
        int node_count = Integer.MAX_VALUE;
        if (props.get(Plugin.PROP_NODE_COUNT) != null) {
            node_count = Integer.parseInt(props.get(Plugin.PROP_NODE_COUNT));
        }
        synchronized (checkins) {
            System.err.println("Order before add: "+checkins);
            if (checkins.containsKey(w.getId())) {
                checkins.remove(w.getId());
            }
            checkins.put(w.getId(), System.currentTimeMillis());
            System.err.println("Order after add: "+checkins);
        }
        int available = getWaiting(true);


        Map<String, Object> result = new HashMap<String, Object>();
        result.put("percent", "" + (float) available / node_count);
        result.put("workers", available);
        return result;
    }

    public int getWaiting(boolean prune) {
        long previous = System.currentTimeMillis() - 10000l;
        int available = 0;
        synchronized (checkins) {
            for (Iterator<Map.Entry<Long, Long>> ei = checkins.entrySet().iterator(); ei.hasNext(); ) {
                if (ei.next().getValue() < previous) {
                    ei.remove();
                } else {
                    break;
                }
            }

            available = checkins.size();
        }
        return available;

    }


    public Session_ assignSession() throws SessionCreationException {
        Session_ result;
        synchronized (checkins) {
            getWaiting(true);


            List<Worker> available = new ArrayList<Worker>(checkins.size());
            for (Long l : checkins.keySet()) {
                available.add(Worker.findWorker(l));
            }
            result = p.createSession(ex, available, force);
            if (result != null) {
                if (force) {
                    force = false;
                }
                checkins.clear();
            }

        }
        return result;
    }




}
