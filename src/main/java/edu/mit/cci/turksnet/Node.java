package edu.mit.cci.turksnet;

import edu.mit.cci.turksnet.jaxb.NodeAdapter;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import edu.mit.cci.turksnet.Session_;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@RooJavaBean
@RooToString
@RooEntity
@XmlRootElement(name = "Node")
@XmlAccessorType(XmlAccessType.NONE)
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlAttribute(name = "Id")
    private Long id;

    private String turkerId;

    @ManyToOne
    private Session_ session_;

    @XmlElement(name = "private")
    private String privateData_;

    @XmlElement(name = "public")
    private String publicData_;

    @XmlElement(name = "neighbor")
     @XmlJavaTypeAdapter(NodeAdapter.class)
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<edu.mit.cci.turksnet.Node> incoming = new HashSet<edu.mit.cci.turksnet.Node>();

    @XmlElement(name = "acceptingInput")
    private boolean acceptingInput = false;

    public Long getId() {
        return this.id;
    }
}
