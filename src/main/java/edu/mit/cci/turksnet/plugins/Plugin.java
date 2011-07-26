package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;

import java.util.Map;
import java.util.Properties;

/**
 * User: jintrone
 * Date: 4/28/11
 * Time: 7:57 AM
 */
public interface Plugin {

    public Session_ createSession(Experiment exp) throws SessionCreationException;
    public void processResults(Node n, String results);

}
