package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.web.NodeForm;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * User: jintrone
 * Date: 7/20/11
 * Time: 3:42 PM
 */
public class TestPlugin implements Plugin {
    @Override
    public Session_ createSession(Experiment exp) throws SessionCreationException {
        Session_ s = new Session_();
        s.setCreated(new Date());
        s.setExperiment(exp);
        s.persist();
        return s;
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
    public void processResults(Node n, Map<String,String> results) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void preprocessProperties(Experiment experiment) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, String> getBonus(Node n) {
        return Collections.emptyMap();
    }


}
