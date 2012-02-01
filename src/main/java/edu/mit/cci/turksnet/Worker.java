package edu.mit.cci.turksnet;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.LockModeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@RooJavaBean
@RooToString
@RooEntity(finders = {"findWorkersByUsernameAndPassword", "findWorkersByLastCheckinGreaterThan", "findWorkersByUsername", "findWorkersByLastCheckinGreaterThanEqualsAndCurrentAssignment"})
public class Worker {

    private String username;

    private String password;

    private Long lastCheckin;

    @Column(columnDefinition = "LONGTEXT")
    private String qualifications;

    @Column(columnDefinition = "LONGTEXT")
    private String training;

    @ManyToOne
    private Session_ currentAssignment;

    @OneToOne
    private WorkerCheckin workerCheckin;


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


    public synchronized void checkin() {
        synchronized (getOrCreateWorkerCheckin().lock()) {
            _checkin();
        }

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
