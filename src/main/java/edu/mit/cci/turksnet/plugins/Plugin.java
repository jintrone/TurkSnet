package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import org.apache.sling.commons.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * User: jintrone
 * Date: 4/28/11
 * Time: 7:57 AM
 */
public interface Plugin {

    public static final String PROP_NODE_COUNT = "node_count";
    public static final String PROP_GRAPH_TYPE = "graph_type";
    public static final String PROP_RUN_STRATEGY = "run_strategy";
    public static final String PROP_ITERATION_COUNT = "iteration_count";
    public static final String PROP_TURN_LENGTH_SECONDS = "turnLength";

    public Session_ createSession(Experiment exp, List<Worker> workers) throws SessionCreationException;

    public boolean checkDone(Session_ s);

    public String getHitCreation(Session_ session, String rooturl);

    public void processResults(Node n, String results);

    public void preprocessProperties(Experiment experiment) throws ExperimentCreationException;

    public String getApplicationBody(Node n) throws Exception;

    public void automateNodeTurn(Node n) throws ClassNotFoundException, JSONException, IllegalAccessException, InstantiationException;

    public long getTurnLength(Experiment experiment);

    Map<String, Object> getScoreInformation(Node n);
}
