package edu.mit.cci.turksnet.util;

import edu.mit.cci.turkit.util.TurkitOutputSink;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import org.apache.sling.commons.json.JSONException;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * User: jintrone
 * Date: 11/7/11
 * Time: 2:25 PM
 */
public interface RunStrategy {

    public void init(Session_ session,TurkitOutputSink beanFieldSink ) throws Exception;

    public void run(boolean repeat) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    public void haltOnNext();

    public boolean isRunning();


    public void updateNode(Node node, String results) throws ClassNotFoundException, IllegalAccessException, InstantiationException, JSONException;

    public Map<String, Object> ping(Worker w, HttpSession session);




    public static enum Type {

        JAVASCRIPT() {
            public RunStrategy instance() {
                return new HeadlessRunner();
            }

        },

        SYNCHRONOUS() {
            public RunStrategy instance() {
                return new SynchroRunner();
            }
        };

        public RunStrategy instance() {
            return null;
        }
    }

}
