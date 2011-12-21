package edu.mit.cci.turksnet.util;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.plugins.SessionCreationException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
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

    public WaitingRoomManager(Experiment e) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.ex = e;
        this.p = e.getActualPlugin();
        final Timer t = new Timer();
        t.schedule(getTimerTask(t), 1000l);


    }

    public TimerTask getTimerTask(final Timer t) {
        return new TimerTask() {

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
    @Transactional
    public Map<String, Object> checkin(Worker w) {
        Worker.entityManager().refresh(w,LockModeType.PESSIMISTIC_WRITE);
        w.setLastCheckin(System.currentTimeMillis());
        Map<String, Object> result = new HashMap<String, Object>();
        TypedQuery<Worker> q = Worker.findWorkersByLastCheckinGreaterThanEqualsAndCurrentAssignmentIsNull(System.currentTimeMillis() - 10000l);
        //q.setLockMode(LockModeType.PESSIMISTIC_READ);
        int available = q.getResultList().size();

        result.put("percent", "" + available / Float.parseFloat(ex.getPropsAsMap().get(Plugin.PROP_NODE_COUNT)));
        result.put("workers", available);
        return result;
    }

    @Transactional
    public Session_ assignSession() throws SessionCreationException {
        List<Worker> available = Worker.findWorkersByLastCheckinGreaterThanEqualsAndCurrentAssignmentIsNull(System.currentTimeMillis() - 10000l).getResultList();
        return p.createSession(ex, available);
    }


}
