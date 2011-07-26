package edu.mit.cci.turksnet;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@RooJavaBean
@RooToString
@RooEntity
public class Results {

    @ManyToOne
    private Session_ session_;

    private String node;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date received;

    private String turkerId;

    private String resultBody;

    private Integer iteration;

    public static Results getLastResults(Session_ s, String node) {
        return null;
    }


}
