package edu.mit.cci.turksnet.plugins;


import edu.mit.cci.snatools.util.jung.DefaultJungEdge;
import edu.mit.cci.snatools.util.jung.DefaultJungGraph;
import edu.mit.cci.snatools.util.jung.DefaultJungNode;
import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.uci.ics.jung.algorithms.generators.Lattice2DGenerator;
import edu.uci.ics.jung.graph.util.Pair;
import org.apache.log4j.Logger;

import javax.jms.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jintrone
 * Date: 4/28/11
 * Time: 7:45 AM
 */


//an experiment maintains settings
//a session is run within an expriment.  it is a case.

public class LoomPlugin implements Plugin {

    public static final String PROP_STORY = "story";
    public static final String PROP_CAUSAL_DEGREE = "item_degree";
    private static final String PROP_NODE_COUNT = "node count";
    private static final String PROP_GRAPH_TYPE = "graph_type";
    private static final String PROP_PRIVATE_TILES = "private_tile_count";


    private static Logger log = Logger.getLogger(LoomPlugin.class);


    public Session_ createSession(Experiment experiment) throws SessionCreationException {
        Map<String, String> props = experiment.getPropsAsMap();
        Session_ session = new Session_(experiment.getId());
        session.setCreated(new Date());
        session.persist();
        try {
            createGraph(props, session);
        } catch (Exception e) {
            throw new SessionCreationException("Error generating graph");
        }
        initNodes(session.getAvailableNodes(), props);
        session.merge();
        return session;
    }

    @Override
    public void processResults(Node n, String results) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void createGraph(Map<String, String> props, Session_ session) throws GraphCreationException {
        String graphtype = props.get(PROP_GRAPH_TYPE);
        Integer nodecount = Integer.parseInt(props.get(PROP_NODE_COUNT));
        DefaultJungGraph graph = null;
        if ("lattice".equals(graphtype)) {
            if (Math.floor(Math.sqrt(nodecount.doubleValue())) < Math.sqrt(nodecount.doubleValue())) {
                log.warn("Requested number of nodes must be a perfect square for lattice networks");
            }
            Lattice2DGenerator<DefaultJungNode, DefaultJungEdge> generator = new Lattice2DGenerator<DefaultJungNode, DefaultJungEdge>(
                    DefaultJungGraph.getFactory(),
                    DefaultJungNode.getFactory(),
                    DefaultJungEdge.getFactory(), (int) Math.sqrt(nodecount.doubleValue()), true);
            graph = (DefaultJungGraph) generator.create();
        } else {
            throw new GraphCreationException("Graph type not supported");
        }

        Map<DefaultJungNode, Node> nodes = new HashMap<DefaultJungNode, Node>();
        for (DefaultJungNode vertex : graph.getVertices()) {
            Node node = new Node();
            node.setSession_(session);
            session.addNode(node);
            nodes.put(vertex, node);

        }

        for (DefaultJungEdge edge : graph.getEdges()) {
            Pair<DefaultJungNode> eps = graph.getEndpoints(edge);
            nodes.get(eps.getSecond()).getIncoming().add(nodes.get(eps.getFirst()));
        }

    }


    private void initNodes(Collection<Node> nodes, Map<String, String> props) {
        int numtiles = Integer.valueOf(props.get(PROP_PRIVATE_TILES));
        String[] story = props.get(PROP_STORY).split(";");
        List<Node> rndnodes = new ArrayList<Node>(nodes);
        List<String> rndstory = new ArrayList<String>(Arrays.asList(story));
        Collections.shuffle(rndnodes);
        int i = 0;
        for (Node n : rndnodes) {
            for (int elt = 0; elt < numtiles; elt++) {
                i = (i + elt) % story.length;
                if (i == 0) {
                    Collections.shuffle(rndstory);
                }
                n.setPrivateData_((n.getPrivateData_() == null ? "" : n.getPrivateData_() + ";") + rndstory.get(i));
            }
            n.persist();
        }


    }

    private String getHitCreation(Session session) {

      return "";
    }

}
