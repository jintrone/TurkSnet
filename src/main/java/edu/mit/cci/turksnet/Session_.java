package edu.mit.cci.turksnet;

import edu.mit.cci.turkit.util.TurkitOutputSink;
import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turkit.util.WireTap;
import edu.mit.cci.turksnet.util.NodeStatus;
import edu.mit.cci.turksnet.util.RunStrategy;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.hibernate.LockMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RooJavaBean
@RooEntity
public class Session_ {

    @Transient
    private Logger log = Logger.getLogger(Session_.class);

    private String network;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;


    private String status;

    @ManyToOne
    private Experiment experiment;

    private int iteration = -1;


    @Transient
    @XmlTransient
    public List<String> test = Arrays.asList("one", "two", "three");

    @Transient
    private static Map<Long, RunStrategy> runners = new HashMap<Long, RunStrategy>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Node> availableNodes = new HashSet<Node>();

    @Column(columnDefinition = "LONGTEXT")
    private String outputLog;

    @Column(columnDefinition = "LONGTEXT")
    private String properties;


    @Transient
    DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    @Transient
    private JSONObject jsonprops = null;

    public String getProperty(String propName) {
        try {
            JSONObject obj = getPropertiesAsJson();

            return obj.has(propName)?getPropertiesAsJson().getString(propName):null;
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public static enum Status {WAITING,RUNNING,ABORTED,COMPLETE}

    private String qualificationRequirements;

    public Session_() {
    }


    public static RunStrategy lookupRunStrategy(long id) {
        return runners.get(id);
    }

    public Session_(long experimentId) {
        Experiment ex = Experiment.findExperiment(experimentId);
        setExperiment(Experiment.findExperiment(experimentId));
        ex.getSessions().add(this);
        setIteration(0);


    }

    public JSONObject getPropertiesAsJson() {
        if (jsonprops == null && properties!=null) {
            try {
                jsonprops = new JSONObject(properties);
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return jsonprops;
    }

    public void updateProperty(String prop, Object value) {
        JSONObject obj = getPropertiesAsJson();
        try {
            obj.put(prop,value);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        properties = obj.toString();

    }

    public void addNode(Node n) {
        getAvailableNodes().add(n);
    }








    public List<Node> getNodesAsList() {
        return new ArrayList<Node>(getAvailableNodes());
    }

    private boolean isRunning() {
        return (getRunner() != null && getRunner().isRunning());
    }



    public Status getStatus() {
        return this.status != null?Status.valueOf(this.status):null;
    }

     public void setStatus(Status s) {
        this.status = s.name();
    }


    @Transactional
    public Node assignNodeToTurker(Worker worker) {
        for (Node n : getAvailableNodes()) {
            if (n.getWorker() == null) {
                n.setWorker(worker);
                n.setStatus(NodeStatus.ASSIGNED.name());
                //n.persist();
                log.debug("Assign worker to "+n.getId()+" in session "+n.getSession_().getId());
                worker.entityManager.refresh(worker,LockModeType.PESSIMISTIC_WRITE);
                worker.setCurrentAssignment(this);
                worker.flush();
                //worker.persist();
                logNodeEvent(n, "assigned");
                return n;
            }
        }
        return null;
    }

    public void logNodeEvent(Node n, String type) {

        SessionLog slog = new SessionLog();
        slog.setDate_(new Date());
        slog.setNode(n);
        slog.setSession_(slog.getSession_());
        slog.setType(type);
        slog.setNodePublicData(n.getPublicData_());
        slog.setNodePrivateData(n.getPrivateData_());
        slog.persist();
    }


    public void run() throws Exception {
        log.info("Starting a run "+getId());



        try {
            setRunner(experiment.getStrategyInstance());
            getRunner().run(true);
             this.setStatus(Status.RUNNING);

        } catch (Exception e) {
            e.printStackTrace();
            updateLog(e.getMessage());
        }
        log.info("Setting status to: "+this.getStatus()+" ("+status+")");
       this.flush();
    }


    public RunStrategy getRunner() {
        return lookupRunStrategy(this.getId());
    }

    public void setRunner(RunStrategy runner) throws Exception {

        runner.init(this, new BeanFieldSink());
        runners.put(this.getId(), runner);
    }

    public void halt() throws Exception {
        if (getRunner() != null) {
            getRunner().haltOnNext();
        } else {
            log.warn("Requested runner to halt, but no runner available");
        }
    }



    private void updateLog(String update) {
        Session_ s = entityManager().find(Session_.class, Session_.this.getId());
        String e = s.getOutputLog() == null ? "" : getOutputLog();
        s.setOutputLog(e + stamp() + update);

    }

    private String stamp() {
        return "\n -- " + format.format(new Date()) + " -- \n";
    }

    public class BeanFieldSink implements TurkitOutputSink {

        WireTap wireTap;

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
    }
}
