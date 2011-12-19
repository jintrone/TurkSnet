package edu.mit.cci.turksnet;

import edu.mit.cci.turksnet.jaxb.NodeAdapter;
import edu.mit.cci.turksnet.util.NodeStatus;
import edu.mit.cci.turksnet.util.U;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RooJavaBean
@XmlRootElement(name = "Node")
@XmlAccessorType(XmlAccessType.NONE)
@RooEntity(finders = {"findNodesByWorker", "findNodesByWorkerAndSession_"})
public class Node {

    @Transient
    private static Node dummy;
    @Transient
    private static String dummyPrivate = "1=There are strange things done&" + "2=I cremated Sam McGee.&" + "3=where the cotton blooms and blows.&" + "4=But the queerest they ever did see&" + "5=Why he left his home in the South to roam Ôround the Pole,";
    @Transient
    private static String dummyPublic1 = "6=in the midnight sun&" + "7=He was always cold,&" + "8=where the cotton blooms and blows.&" + "9=The Arctic trails have their secret tales&" + "10=That would make your blood run cold&" + "3=where the cotton blooms and blows.&" + "4=But the queerest they ever did see";
    @Transient
    private static String dummyPublic2 = "11=By the men who moil for gold&" + "12=Was that night on the marge of Lake Lebarge&" + "13=God only knows.&" + "14=The Northern Lights have seen queer sights&" + "15=but the land of gold seemed to hold him like a spell";
    @Transient
    private static String dummyPublic3 = "16=Now Sam McGee was from Tennesse,&" + "17=were the cotton blooms and blows&" + "18=Though he'd often say in his homely way&" + "19=that he'd sooner live in hell&" + "20=On a Christmas Day we were mushing our way&" + "15=but the land of gold seemed to hold him like a spell";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlAttribute(name = "Id")
    private Long id;

    @ManyToOne
    private Session_ session_;

    @XmlElement(name = "private")
     @Column(columnDefinition = "LONGTEXT")
    private String privateData_;

    @XmlElement(name = "public")
     @Column(columnDefinition = "LONGTEXT")
    private String publicData_;

    @XmlElement(name = "neighbor")
    @XmlJavaTypeAdapter(NodeAdapter.class)
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<edu.mit.cci.turksnet.Node> incoming = new HashSet<edu.mit.cci.turksnet.Node>();

    private String status = NodeStatus.UNASSIGNED.name();

    private String metaData;

    @ManyToOne
    private Worker worker;

    public Long getId() {
        return this.id;
    }

    public JSONObject getJsonData() {
        JSONObject result = new JSONObject();
        try {
            result.put("privateData", listStoryData(getPrivateData_()));
            result.put("publicData", listStoryData(getPublicData_()));

            JSONObject incoming = new JSONObject();
            for (Node i : getIncoming()) {
                incoming.put(i.getId() + "", listStoryData(i.getPublicData_()));

            }
            result.put("incomingData", incoming);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
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
            Node neighbor3 = new Node();
            neighbor3.setId(-4l);
            neighbor3.setPublicData_(dummyPublic3);
            dummy.incoming.add(neighbor1);
            dummy.incoming.add(neighbor2);
            dummy.incoming.add(neighbor3);
        }
        return dummy;
    }

    /**
     * Designed to preserve order
     *
     * @param data
     * @return
     */
    public static Map<String, Object> mapifyStoryDataOld(String data) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        if (data != null && !data.isEmpty()) {
            for (String s : data.split("&")) {
                if (s.isEmpty()) continue;
                String[] sp = s.split("=");
                result.put(sp[0], sp[1]);
            }
        }
        return result;
    }

    public static List listStoryData(String data) {
        List result = new ArrayList();
        if (data != null && !data.isEmpty()) {
            for (String elt : data.split("&")) {
                if (elt.isEmpty()) continue;
                String[] sp = elt.split("=");
                JSONObject obj = new JSONObject();
                try {
                    obj.put(sp[0], sp[1]);
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                result.add(obj);
            }
        }

        return result;
    }

    public static void main(String[] args) throws JSONException {
        Node n = getDummyNode();

        System.err.println(U.safejson(n.getJsonData()));

    }

}
