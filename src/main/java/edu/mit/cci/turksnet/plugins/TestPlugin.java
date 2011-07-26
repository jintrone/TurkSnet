package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;

import java.util.Date;

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
    public void processResults(Node n, String results) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
