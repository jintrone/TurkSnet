package edu.mit.cci.turksnet.util;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.WorkerCheckin;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.plugins.SessionCreationException;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Session;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.Date;
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

            w.checkin();


        Map<String, Object> result = new HashMap<String, Object>();
        Date previous = new Date(System.currentTimeMillis() - 10000l);
        TypedQuery<Worker> q = WorkerCheckin.findAvailableWorkers(previous);
        //q.setLockMode(LockModeType.PESSIMISTIC_READ);
        int available = q.getResultList().size();
        Map<String,String> props = ex.getPropsAsMap();
        int node_count = Integer.MAX_VALUE;
        if (props.get(Plugin.PROP_NODE_COUNT) != null) {
            node_count =  Integer.parseInt(props.get(Plugin.PROP_NODE_COUNT));
        }

        result.put("percent", "" + (float)available / node_count);
        result.put("workers", available);
        return result;
    }

    public int getWaiting() {

         Date previous = new Date(System.currentTimeMillis() - 10000l);

        return  WorkerCheckin.findAvailableWorkers(previous).getResultList().size();
    }


    public Session_ assignSession() throws SessionCreationException {

         Date previous = new Date(System.currentTimeMillis() - 10000l);
        List<Worker> available = WorkerCheckin.findAvailableWorkers(previous).getResultList();
        Session_ result =  p.createSession(ex, available, force);
        if (result!=null && force) {
            force = false;
        }
        return result;
    }


}
