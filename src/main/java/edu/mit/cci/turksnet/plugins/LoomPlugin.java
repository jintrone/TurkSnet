package edu.mit.cci.turksnet.plugins;


import edu.mit.cci.snatools.util.jung.DefaultJungEdge;
import edu.mit.cci.snatools.util.jung.DefaultJungGraph;
import edu.mit.cci.snatools.util.jung.DefaultJungNode;
import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.SessionLog;
import edu.mit.cci.turksnet.Session_;
import edu.uci.ics.jung.algorithms.generators.Lattice2DGenerator;
import edu.uci.ics.jung.graph.util.Pair;
import org.apache.log4j.Logger;

import javax.mail.Session;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: jintrone
 * Date: 4/28/11
 * Time: 7:45 AM
 */


//an experiment maintains settings
//a session is run within an expriment.  it is a case.

public class LoomPlugin implements Plugin {

    public static final String PROP_STORY = "story";
    public static final String PROP_ASSIGNMENT_VALUE="assignment_value";
    private static final String PROP_NODE_COUNT = "node_count";
    private static final String PROP_GRAPH_TYPE = "graph_type";
    private static final String PROP_PRIVATE_TILES = "private_tile_count";
    public static final String PROP_ITERATION_COUNT = "iteration_count";
    public static final String PROP_SESSION_BONUS_VALUE = "sessionBonusValue";
    public static final String PROP_SESSION_BONUS_COUNT = "sessionBonusCount";
    public static final String PROP_SESSION_BONUS_CORRECT = "sessionBonusCorrect";


    private static final String PROP_HIT_TITLE = "title";
    private static final String PROP_HIT_DESCRIPTION = "description";
    private static final String PROP_HIT_AUTO_APPROVAL_DELAY="autoApprovalDelayInSeconds";
    private static final String PROP_HIT_ASSIGNMENT_DURATION="assignmentDurationInSeconds";
    private static final String PROP_HIT_KEYWORDS="keyword";
    private static final String PROP_HIT_HEIGHT="height";

    private static Logger logger = Logger.getLogger(LoomPlugin.class);


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
    public boolean checkDone(Session_ s) {
        for (Node n : s.getAvailableNodes()) {
            if (n.isAcceptingInput()) {
                return false;
            }
        }
        return (s.getIteration() >= Integer.parseInt(s.getExperiment().getPropsAsMap().get(PROP_ITERATION_COUNT)));

    }

    @Override
    public void processResults(Node n, Map<String,String> results) {
        n.setPublicData_(results.get("publicData"));
        n.setPrivateData_(results.get("privateData"));
        n.persist();
    }

    private static int getNewId(Set<Integer> set) {
        Random rnd = new Random();
        int n;
        do {
            n = rnd.nextInt(1000);

        } while (set.contains(n));
        set.add(n);
        return n;
    }

    @Override
    public void preprocessProperties(Experiment experiment) throws ExperimentCreationException {
        StringBuilder builder = new StringBuilder();
        Map<String, String> props = experiment.getPropsAsMap();
        String story = props.get(PROP_STORY);
        Pattern pat = Pattern.compile("(\\d+):([\\w\\s]+)");
        if (story == null) throw new ExperimentCreationException("No story associated with Loom Experiment");
        String sep = "";
        Set<Integer> ids = new HashSet<Integer>();
        for (String p : story.split(";")) {
            p = p.trim();
            builder.append(sep);
            Matcher m = pat.matcher(p);
            if (m.matches()) {
                if (!ids.contains(m.group(1))) {
                    ids.add(Integer.getInteger(m.group(1)));
                    builder.append(p);
                } else {
                    int nid = getNewId(ids);
                    builder.append(nid).append(":").append(m.group(2));
                }
            } else {
                int nid = getNewId(ids);
                builder.append(nid).append(":").append(p);

            }
            sep = ";";
        }
        experiment.updateProperty(PROP_STORY, builder.toString());
    }

    private void createGraph(Map<String, String> props, Session_ session) throws GraphCreationException {
        String graphtype = props.get(PROP_GRAPH_TYPE);
        Integer nodecount = Integer.parseInt(props.get(PROP_NODE_COUNT));
        DefaultJungGraph graph = null;
        if (nodecount == 1) {
            graph = new DefaultJungGraph();
            DefaultJungNode node = DefaultJungNode.getFactory().create();
            graph.addVertex(node);
        }
        else if ("lattice".equals(graphtype)) {
            if (Math.floor(Math.sqrt(nodecount.doubleValue())) < Math.sqrt(nodecount.doubleValue())) {
                logger.warn("Requested number of nodes must be a perfect square for lattice networks");
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

    @Override
    public String getHitCreation(Session_ session, String rooturl) {
        Map<String,String> props = session.getExperiment().getPropsAsMap();
        String val = props.get(PROP_ASSIGNMENT_VALUE);
        Map<String, String> result = new HashMap<String, String>();
        result.put("title", props.get(PROP_HIT_TITLE));
        result.put("desc", props.get(PROP_HIT_DESCRIPTION));
        result.put("url", rooturl + "/session_s/" + session.getId() + "/turk/app");
        result.put("autoApprovalDelayInSeconds",props.get(PROP_HIT_AUTO_APPROVAL_DELAY));
        result.put("assignmentDurationInSeconds",props.get(PROP_HIT_ASSIGNMENT_DURATION));
        result.put("reward", val);
        result.put("assignments", props.get(PROP_NODE_COUNT));
        result.put("keywords",props.get(PROP_HIT_KEYWORDS));
        result.put("height", props.get(PROP_HIT_HEIGHT));
        if (session.getQualificationRequirements()!=null ) {
            result.put("qualificationRequirements", createQualificationString(session.getQualificationRequirements()));
        }
        return "("+jsonify(result)+")";

    }

    private static String createQualificationString(String qual) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("QualificationTypeId",qual);
        map.put("Comparator","Exists");
        return jsonify(map);
    }

    private static String jsonify(Map<String, String> vals) {
        StringBuilder buffer = new StringBuilder();
        String sep = "";
        buffer.append("{");
        for (Map.Entry<String, String> ent : vals.entrySet()) {
            buffer.append(sep).append(ent.getKey()).append(":");
            if (ent.getValue().startsWith("{") && ent.getValue().endsWith("}")) {
                buffer.append(ent.getValue());
            }
            else if (!ent.getValue().matches("[\\d\\.]+")) {
                buffer.append('"').append(ent.getValue()).append('"');
            } else {
                buffer.append(ent.getValue());
            }
            sep = ",";
        }
        buffer.append("}");
        return buffer.toString();
    }

    public static List<Float> getSessionScores(Experiment experiment, List<SessionLog> logs) {
        List<Float> result = new ArrayList<Float>();
        List<Integer> truth = getStoryOrder(experiment.getPropsAsMap().get(PROP_STORY));
        for (SessionLog log:logs) {
            result.add(score(truth,getStoryOrder(log.getNodePublicData())));
        }
        logger.debug("Resulting scores are: "+result);
        return result;
    }


    public Map<String,String> getBonus(Node n) {
        List<SessionLog> logs = new ArrayList<SessionLog>();
        for (SessionLog log :SessionLog.findAllSessionLogs()) {
            if (n.getId().equals(log.getNode().getId()) && log.getType().equals("results")) {
                logger.debug("Adding session log "+log.getNodePublicData());
                logs.add(log);
            }
        }
        Experiment e = n.getSession_().getExperiment();

        List<Float> scores = getSessionScores(n.getSession_().getExperiment(),logs);
        String description = "No score could be obtained";
        String bonus = "0.0";
        if (scores.isEmpty()) {
            logger.debug("No scores found");
           return Collections.emptyMap();


        }
        Float lastscore = scores.get(scores.size()-1);
        Collections.sort(scores,new Comparator<Float>() {
            @Override
            public int compare(Float aFloat, Float aFloat1) {
                return -1 * (aFloat.compareTo(aFloat1));
            }
        });

        scores.subList(0,Math.min(scores.size(),Integer.parseInt(e.getPropsAsMap().get(PROP_SESSION_BONUS_COUNT))));
        StringBuilder builder = new StringBuilder();
        builder.append("Your best session scores were: ");
        float subtotal = 0;
        float total = 0;
        for (Float f:scores) {
            builder.append(String.format("%.2f",f)).append(" ");
           subtotal+=f*Float.parseFloat(e.getPropsAsMap().get(PROP_SESSION_BONUS_VALUE));
        }
        builder.append("\n").append("Your final session score was: ").append(String.format("%.2f",lastscore));
        total =subtotal + (lastscore == 1.0f?Float.parseFloat(e.getPropsAsMap().get(PROP_SESSION_BONUS_CORRECT)):0.0f);


        Map<String,String> result = new HashMap<String,String>();
        result.put("Description",builder.toString());
        result.put("Bonus",String.format("%.2f",total));
        result.put("CumulativeBonus",String.format("%.2f",subtotal));

        logger.debug("Bonus structure: "+result);
        return result;
    }

    private static List<Integer> getStoryOrder(String story) {
      List<Integer> result = new ArrayList<Integer>();
        logger.debug("Extracting story from "+story);
        Pattern pat = Pattern.compile("(\\d+):[\\w\\s]+");
        if (story!=null) {
            for (String p : story.split(";")) {
                p = p.trim();
                Matcher m = pat.matcher(p);
                if (m.matches()) {
                    result.add(Integer.parseInt(m.group(1)));
                }
            }
        }
        logger.debug("Got story order: "+result);
        return result;
   }

    public static Float score(List<Integer> truth, List<Integer> sample) {
        logger.debug("Checking truth:"+truth+" against sample:"+sample);
        Map<Integer,Integer> tmap = new HashMap<Integer,Integer>();
        int i = 0;
        for (Integer t:truth) {
            tmap.put(t,i++);
        }
        tmap.keySet().retainAll(sample);


        int last = -1;
        int accountedFor = 0;
        for (Integer s:sample) {
            if (last > -1) {
                if (tmap.get(last) < tmap.get(s)) {
                    accountedFor++;
                }
            }
            last= s;

        }
        return accountedFor / (float)(truth.size()-1);


    }

    public static void main(String[] args) {
        String truth = "3:there was a fox and a bear;209:who were friends;87:one day they decided to catch a chicken for supper;262:they decided to go together;849:because neither one wanted to be left alone;369:and they both liked fried chicken;";
        String sample = "262:they decided to go together;849:because neither one wanted to be left alone;369:and they both liked fried chicken;3:there was a fox and a bear;209:who were friends;";
        System.err.println(score(getStoryOrder(truth),getStoryOrder(sample)));

    }



}
