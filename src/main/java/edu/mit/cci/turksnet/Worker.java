package edu.mit.cci.turksnet;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import edu.mit.cci.turksnet.Session_;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findWorkersByUsernameAndPassword", "findWorkersByLastCheckinGreaterThan", "findWorkersByUsername", "findWorkersByLastCheckinGreaterThanEqualsAndCurrentAssignment" })
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
        if (workerCheckin!=null) {
            result = System.currentTimeMillis() - workerCheckin.getLastCheckIn().getTime();
        }
        return result;


    }

    public void checkin() {
      WorkerCheckin ck = getWorkerCheckin();
        if (ck == null) {
            ck = new WorkerCheckin();
            ck.setWorker(this);
            this.setWorkerCheckin(ck);
            ck.persist();
        }
        Date current = new Date();
        ck.setLastCheckIn(current);
        ck.flush();
    }
}
