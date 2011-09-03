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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@RooJavaBean
@RooToString
@RooEntity
@XmlRootElement(name = "Node")
@XmlAccessorType(XmlAccessType.NONE)
public class Node {

    @Transient
    private static Node dummy;

    private static String dummyPrivate = "1:There are strange things done;"+
            "2:I cremated Sam McGee.;"+
            "3:where the cotton blooms and blows.;"+
            "4:But the queerest they ever did see;"+
            "5:Why he left his home in the South to roam Ôround the Pole,";


    private static String dummyPublic1 = "6:in the midnight sun;"+
            "7:He was always cold,;"+
            "8:where the cotton blooms and blows.;"+
            "9:The Arctic trails have their secret tales;"+
            "10:That would make your blood run cold;";


    private static String dummyPublic2 = "11:By the men who moil for gold;"+
            "12:Was that night on the marge of Lake Lebarge;"+
            "13:God only knows.;"+
            "14:The Northern Lights have seen queer sights;"+
            "15:but the land of gold seemed to hold him like a spell;";


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


    @Transient
    public static Node getDummyNode() {
        if (dummy == null) {
            dummy = new Node();
            dummy.setId(-1l);
            dummy.setPrivateData_(dummyPrivate);
            dummy.setPublicData_("");


        Node neighbor1 = new Node();
        neighbor1.setId(-2l);
        neighbor1.setPublicData_(dummyPublic1);

        Node neighbor2 = new Node();
        neighbor2.setId(-3l);
        neighbor2.setPublicData_(dummyPublic2);
            dummy.incoming.add(neighbor1);
            dummy.incoming.add(neighbor2);

        }

        return dummy;
    }
}
