package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;


import java.util.List;
import java.util.Map;

/**
 * User: jintrone
 * Date: 4/28/11
 * Time: 7:57 AM
 */
public interface Plugin {




    String getQualificationApp() throws Exception;

    String getTrainingApp() throws Exception;

    String getLoginApp() throws Exception;

    JSONObject getTrainingData(Worker w, Experiment e, Map parameterMap);

    void addTrainingData(Worker w, Experiment e, Map parameterMap);

    Map<String,Object> getFinalInfo(Node n);

    int getRemainingSessions(Experiment e);



    public static enum Event {
        VISIT, LOGIN, REGISTER, QUALIFICATIONS_SUBMITTED, TRAINING_SUBMITTED

    }

    public static enum Destination {
        LOGIN("/experiments/%d/login"),
        QUALIFICATIONS("/experiments/%d/qualifications"),
        TRAINING("/experiments/%d/training"),
        WAITING("/experiments/%d/waiting");

        String template;

        Destination(String template) {
            this.template = template;
        }

        public String url(Long id) {
            return String.format(template,id);
        }
    }

    public static final String PROP_NODE_COUNT = "node_count";
    public static final String PROP_GRAPH_TYPE = "graph_type";
    public static final String PROP_RUN_STRATEGY = "run_strategy";
    public static final String PROP_ITERATION_COUNT = "iteration_count";
    public static final String PROP_TURN_LENGTH_SECONDS = "turnLength";
    public static final String PROP_SESSION_COUNT = "session_count";
    public static final String PROP_SESSION_ID = "sessionid";

    public Session_ createSession(Experiment exp, List<Worker> workers,boolean force) throws SessionCreationException;

    public boolean checkDone(Session_ s);

    public String getHitCreation(Session_ session, String rooturl);

    public void processResults(Node n, String results);

    public String getApplicationBody(Node n) throws Exception;

    public void automateNodeTurn(Node n) throws ClassNotFoundException, JSONException, IllegalAccessException, InstantiationException;

    public long getTurnLength(Session_ session);

    Map<String, Object> getScoreInformation(Node n);

    public Destination getDestinationForEvent(Worker w, Event e);


}
