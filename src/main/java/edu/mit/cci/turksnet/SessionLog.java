package edu.mit.cci.turksnet;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import edu.mit.cci.turksnet.Session_;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import edu.mit.cci.turksnet.Node;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class SessionLog {

    @ManyToOne
    private Session_ session_;

    @ManyToOne
    private Node node;

     @Column(columnDefinition = "LONGTEXT")
    private String nodePrivateData;

     @Column(columnDefinition = "LONGTEXT")
    private String nodePublicData;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date date_;
}
