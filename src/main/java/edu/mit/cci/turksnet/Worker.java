package edu.mit.cci.turksnet;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import edu.mit.cci.turksnet.Session_;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

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
}
