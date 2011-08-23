package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.web.NodeForm;


import java.util.Map;
import java.util.Properties;

/**
 * User: jintrone
 * Date: 4/28/11
 * Time: 7:57 AM
 */
public interface Plugin {

    public Session_ createSession(Experiment exp) throws SessionCreationException;
    public boolean checkDone(Session_ s);
    public String getHitCreation(Session_ session, String rooturl);
    public void processResults(Node n,Map<String,String> results);

    public void preprocessProperties(Experiment experiment) throws ExperimentCreationException;
}
