package edu.mit.cci.turksnet;

import com.sun.istack.internal.Nullable;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.LockModeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RooJavaBean
@RooToString
@RooEntity(finders = {"findWorkersByUsernameAndPassword", "findWorkersByLastCheckinGreaterThan", "findWorkersByUsername", "findWorkersByLastCheckinGreaterThanEqualsAndCurrentAssignment"})
public class Worker {

    private String username;

    private String password;

    @Column(columnDefinition = "TIMESTAMP")
    private Date lastCheckin;

     @Column(columnDefinition = "TIMESTAMP")
    private Date lastAttempt;

    @Column(columnDefinition = "LONGTEXT")
    private String qualifications;

    @Column(columnDefinition = "LONGTEXT")
    private String training;

    private Integer participation;

    @ManyToOne
    @Nullable
    private Session_ currentAssignment;

    @OneToOne
    private WorkerCheckin workerCheckin;

    @Transient
    private static final Map<Long,Date> checkins = Collections.synchronizedMap(new HashMap<Long, Date>());


    public Long getCheckin() {
        Long result = Long.MAX_VALUE;
        if (workerCheckin != null) {
            result = System.currentTimeMillis() - workerCheckin.getLastCheckIn().getTime();
        }
        return result;


    }

    public WorkerCheckin getOrCreateWorkerCheckin() {
        if (workerCheckin == null) {
            workerCheckin = new WorkerCheckin();
            workerCheckin.setWorker(this);
            this.setWorkerCheckin(workerCheckin);
            workerCheckin.persist();

        }
        return workerCheckin;
    }


    @Transactional
    public void setLastLogon() {
      lastCheckin = new Date();
        flush();

    }

    @Transactional
    public void setLastAttempt() {
      lastAttempt = new Date();
        flush();

    }


    public void checkin() {
//        checkins.put(getId(),new Date());
        synchronized (getOrCreateWorkerCheckin().lock()) {
            _checkin();
        }

    }

    public static int countAvailableWorkerSince(Date d, boolean prune) {
        Map<Long,Date> copy = new HashMap<Long, Date>(checkins);
        int total = 0;
        for (Map.Entry<Long,Date> ent:copy.entrySet()) {
            if (ent.getValue().before(d)) {
                checkins.remove(ent.getKey());
            } else if (Worker.findWorker(ent.getKey()).getCurrentAssignment()==null) {
                total++;
            }

        }
        return total;
    }

    public Integer getParticipation() {
        return participation;
    }

    public void setParticipation(Integer i) {
       this.participation = i;
    }

    @Transactional
    public void _checkin() {
        workerCheckin.entityManager.refresh(workerCheckin,LockModeType.PESSIMISTIC_WRITE);
        Date current = new Date();
        workerCheckin.setLastCheckIn(current);
        System.err.println("Thread-" + Thread.currentThread().getId() + ": set checkin " + current);
        try {
            Thread.sleep((long) Math.random() * 1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            System.err.println("Thread-" + Thread.currentThread().getId() + ": try to flush " + current);
            workerCheckin.flush();
        } catch (RuntimeException ex) {
            System.err.println("Got flush error: " + ex.getMessage());
        }
    }


}
