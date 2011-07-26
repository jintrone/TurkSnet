package edu.mit.cci.turksnet;

import edu.mit.cci.turkit.gui.HeadlessRunner;
import edu.mit.cci.turkit.util.TurkitOutputSink;
import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turkit.util.WireTap;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import sun.rmi.runtime.Log;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@RooJavaBean
@RooToString
@RooEntity
public class Session_ {

    @Transient
    private Logger log = Logger.getLogger(Session_.class);

    private String network;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;

    private Boolean active;

    @ManyToOne
    private Experiment experiment;

    private int iteration;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Node> pendingNodes = new HashSet<Node>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Node> availableNodes = new HashSet<Node>();

    @Column(columnDefinition = "LONGTEXT")
    private String outputLog;

    @Column(columnDefinition = "LONGTEXT")
    private String properties;


    @Transient
    Map<String,String> propertiesAsMap = null;

    @Transient
    HeadlessRunner runner;

    public Session_() {


    }

    public Session_(long experimentId) {
        setExperiment(Experiment.findExperiment(experimentId));
         setIteration(0);
        setActive(true);
    }

    public void addNode(Node n) {
      getAvailableNodes().add(n);
    }

    public Map<String,String> getPropertiesAsMap() {
       if (propertiesAsMap == null) {
           propertiesAsMap = new HashMap<String,String>();
           propertiesAsMap.putAll(U.splitProperties(getProperties()));
       }
        return Collections.unmodifiableMap(propertiesAsMap);
    }

    public void storePropertyMap() {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String,String> ent:getPropertiesAsMap().entrySet()) {
          buffer.append(ent.getKey()).append("=").append(ent.getValue()).append("\n");
        }
        setProperties(buffer.toString());
    }


    public void updateProperty(String property,Object value) {
        if (propertiesAsMap == null) {
            getPropertiesAsMap();
        }
        propertiesAsMap.put(property,value.toString());
        storePropertyMap();
        merge();
    }

    public String getHitCreationString() {
        return "";
    }

    public void registerHit(String hitid) {
    }

    public Node getNodeForAssignment(String workerId, String assignmentId) {
        return null;
    }

    public void processNodeResults(String turkerId, String results) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       Node n = findNodeForTurker(turkerId);
       if (n == null) {
           log.info("Could not identify node for turker " + turkerId);
        return;
       } else {
            experiment.getActualPlugin().processResults(n,results);
            logNodeEvent(n,"results");
       }
    }

    public Node findNodeForTurker(String turkerId) {
        for (Node n:availableNodes) {
            if (turkerId.equals(n.getTurkerId())) {
                return n;
            }
        }
        return null;
    }

    private void logNodeEvent(Node n,String type) {

    }

    public void run() throws Exception {
        this.active = true;
        this.merge();
        runner = new HeadlessRunner(new BeanFieldSink());
        runner.loadScript("Experiment:" + experiment.getId() + "_Session:" + getId(), experiment.getJavaScript(), experiment.getPropsAsMap(), this);
        runner.run(true);
        this.active = true;
        this.merge();
    }

    public class BeanFieldSink implements TurkitOutputSink {

        WireTap wireTap;
        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

        @Override
        public void startCapture() {
            wireTap = new WireTap();
        }

        @Override
        public void stopCapture() {
             updateLog(wireTap.close());
            wireTap = null;
        }

        @Override
        public void setText(String text) {
           updateLog(text);

        }

        private void updateLog(String update) {
            String e = getOutputLog() == null ? "" : getOutputLog();
            setOutputLog(e + stamp() + update);
            Session_.this.merge();
        }

        private String stamp() {
            return "\n -- " + format.format(new Date()) + " -- \n";
        }
    }

}
