package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import org.apache.sling.commons.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jintrone
 * Date: 7/20/11
 * Time: 3:42 PM
 */
public class TestPlugin implements Plugin {


    @Override
    public String getQualificationApp() throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTrainingApp() throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getLoginApp() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject getTrainingData(Worker w, Experiment e, Map parameterMap) {
        return null;


    }

    @Override
    public void addTrainingData(Worker w, Experiment e, Map parameterMap) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, Object> getFinalInfo(Node n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getRemainingSessions(Experiment e) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public Session_ createSession(Experiment exp, List<Worker> workers, boolean force) throws SessionCreationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean checkDone(Session_ s) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getHitCreation(Session_ session, String rooturl) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void processResults(Node n,String results) {
        //To change body of implemented methods use File | Settings | File Templates.
    }




    @Override
    public String getApplicationBody(Node n) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void automateNodeTurn(Node n) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getTurnLength(Session_ session) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public Map<String, Object> getScoreInformation(Node n) {
        return Collections.emptyMap();
    }

    @Override
    public Destination getDestinationForEvent(Worker w, Event e) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
