package edu.mit.cci.turksnet;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import edu.mit.cci.turksnet.Session_;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findWorkersByUsernameAndPassword", "findWorkersByLastCheckinGreaterThan", "findWorkersByUsername", "findWorkersByLastCheckinGreaterThanEqualsAndCurrentAssignment" })
public class Worker {

    private String username;

    private String password;

    private Long lastCheckin;

    private String qualifications;

    @ManyToOne
    private Session_ currentAssignment;
}
