package edu.mit.cci.turksnet.jaxb;

import edu.mit.cci.turksnet.Node;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * User: jintrone
 * Date: 7/26/11
 * Time: 9:14 AM
 */
@XmlRootElement(name = "NodeReference")
public class NodeAdapter extends XmlAdapter<NodeAdapter, Node> {


    @XmlAttribute(name = "id")
    Long id;

    @XmlElement(name = "data")
    String data;



    public NodeAdapter() {}

    public NodeAdapter(Node n) {
        this.id = n.getId();
        this.data = n.getPublicData_();

    }


    @Override
    public Node unmarshal(NodeAdapter nodeAdapter) throws Exception {
        //nothin doin right now
        return null;
    }

    @Override
    public NodeAdapter marshal(Node node) throws Exception {
        return new NodeAdapter(node);
    }
}

