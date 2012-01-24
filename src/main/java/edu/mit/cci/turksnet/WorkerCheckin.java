package edu.mit.cci.turksnet;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: jintrone
 * Date: 1/23/12
 * Time: 6:41 AM
 */

@Entity
@Configurable
public class WorkerCheckin {




    @PersistenceContext
    transient EntityManager entityManager;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;


    @Transient
    static Map<Long,Object> locks = new HashMap<Long,Object>();

    @OneToOne
    private Worker worker;

    public Date getLastCheckIn() {
        return lastCheckIn;
    }

    public  Object lock() {
        if (!locks.containsKey(this.id)) {
            locks.put(this.id,new Object());
        }

        return locks.get(this.id);
    }

    public void setLastCheckIn(Date lastCheckIn) {
        this.lastCheckIn = lastCheckIn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "SS")
    private Date lastCheckIn;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static final EntityManager entityManager() {
        EntityManager em = new Worker().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Worker attached = Worker.findWorker(this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public WorkerCheckin merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        WorkerCheckin merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }



    public static TypedQuery<Worker> findAvailableWorkers(Date lastCheckin) {
        if (lastCheckin == null) throw new IllegalArgumentException("The lastCheckin argument is required");
        EntityManager em = entityManager();
        //TypedQuery<Worker> q = em.createQuery("SELECT wc.worker FROM WorkerCheckin wc WHERE wc.checkIn >= :lastCheckin and wc.worker.currentAssignment IS NULL", Worker.class);
        TypedQuery<Worker> q = em.createQuery("SELECT wc.worker FROM WorkerCheckin wc JOIN wc.worker w WHERE wc.lastCheckIn >= :lastCheckin and w.currentAssignment IS NULL", Worker.class);
        q.setParameter("lastCheckin", lastCheckin);

        return q;
    }
}
