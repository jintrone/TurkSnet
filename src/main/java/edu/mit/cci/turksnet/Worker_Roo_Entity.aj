// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet;

import edu.mit.cci.turksnet.Worker;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Worker_Roo_Entity {
    
    declare @type: Worker: @Entity;
    
    @PersistenceContext
    transient EntityManager Worker.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Worker.id;
    
    @Version
    @Column(name = "version")
    private Integer Worker.version;
    
    public Long Worker.getId() {
        return this.id;
    }
    
    public void Worker.setId(Long id) {
        this.id = id;
    }
    
    public Integer Worker.getVersion() {
        return this.version;
    }
    
    public void Worker.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Worker.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Worker.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Worker attached = Worker.findWorker(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Worker.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Worker Worker.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Worker merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Worker.entityManager() {
        EntityManager em = new Worker().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Worker.countWorkers() {
        return entityManager().createQuery("select count(o) from Worker o", Long.class).getSingleResult();
    }
    
    public static List<Worker> Worker.findAllWorkers() {
        return entityManager().createQuery("select o from Worker o", Worker.class).getResultList();
    }
    
    public static Worker Worker.findWorker(Long id) {
        if (id == null) return null;
        return entityManager().find(Worker.class, id);
    }
    
    public static List<Worker> Worker.findWorkerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Worker o", Worker.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
