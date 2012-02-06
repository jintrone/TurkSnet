package edu.mit.cci.turksnet;

import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turksnet.constraints.ValidClass;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.util.RunStrategy;
import edu.mit.cci.turksnet.util.WaitingRoomManager;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RooJavaBean
@RooEntity
public class Experiment {

    @Column(columnDefinition = "LONGTEXT")
    private String properties;

    @Column(columnDefinition = "LONGTEXT")
    private String network;

    @Column(columnDefinition = "LONGTEXT")
    private String javaScript;

    @Column(columnDefinition = "LONGTEXT")
    private String sessionProps;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "SS")
    private Date startDate;

    @ValidClass
    private String pluginClazz;

    @Transient
    private static Plugin actual;

    @Transient
    private Map<String, String> propsAsMap;

    private Boolean running= false;

    @Transient
       private static Map<Long, WaitingRoomManager> managers = new HashMap<Long, WaitingRoomManager>();



     @Transient
    private static Logger log = Logger.getLogger(Experiment.class);

     @Transient
    private ExperimentRunner runner = new ExperimentRunner();


    public Plugin getActualPlugin() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (actual == null) {
            actual = (Plugin) Class.forName(pluginClazz).newInstance();
        }
        return actual;
    }



    public RunStrategy getStrategyInstance() {
        return RunStrategy.Type.valueOf(getProperty(Plugin.PROP_RUN_STRATEGY)).instance();
    }

    public List<Session_> getSessions() {
        List<Session_> result = new ArrayList<Session_>(Session_.findAllSession_s());
        for (Iterator<Session_> i = result.iterator(); i.hasNext(); ) {
            if (i.next().getExperiment().equals(this)) continue;
            i.remove();
        }
        return result;
    }

    public String getSessionProps() {
        return sessionProps;
    }

    public void setSessionProps(String props) {
        this.sessionProps = props;
    }

    public Map<String, String> getPropsAsMap() {
        if (propsAsMap == null) {
            propsAsMap = new HashMap<String, String>();
            propsAsMap.putAll(U.splitProperties(getProperties()));
        }
        return propsAsMap;
    }



    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Properties: ").append(getProperties()).append(", ");
        //sb.append("Network: ").append(getNetwork()).append(", ");
        sb.append("PluginClazz: ").append(getPluginClazz());
        return sb.toString();
    }

    public void storePropertyMap() {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> ent : getPropsAsMap().entrySet()) {
            buffer.append(ent.getKey()).append("=").append(ent.getValue()).append("\n");
        }
        setProperties(buffer.toString());
    }

    public void updateProperty(String property, Object value) {
        if (propsAsMap == null) {
            getPropsAsMap();
        }
        propsAsMap.put(property, value.toString());
        storePropertyMap();
    }

    public static WaitingRoomManager getWaitingRoomManager(long id) {
        Experiment e = Experiment.findExperiment(id);
        WaitingRoomManager manager = null;
        if (e == null || !e.getRunning()) {
          log.error("Experiment "+id+" is not available");
        } else {
            manager = managers.get(id);
            if (manager == null) {
                try {
                    manager = new WaitingRoomManager(e);
                    managers.put(id,manager);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InstantiationException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
        return manager;

    }

   public void run() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
       this.setRunning(true);
       this.flush();
       getWaitingRoomManager(this.getId());


   }



    public int getAvailableSessionCount() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return getActualPlugin().getRemainingSessions(this);

    }

    public List<Session_> getActiveSessions() {
        List<Session_> sessions = new ArrayList<Session_>(getSessions());
        for (Iterator<Session_> i = sessions.iterator();i.hasNext();) {
           if (i.next().getStatus()!= Session_.Status.RUNNING) {
                i.remove();
           }
        }
        return sessions;

    }

    public void setStartDate(Date date_) {
        this.startDate = date_;
        this.flush();
        runner.updateExperiment(this);
    }

     public void setRunning(Boolean running) {
        this.running = running;
         runner.updateExperiment(this);
    }


    public void stop() {
        this.setRunning(false);

    }

    public void forceRun() {
       getWaitingRoomManager(this.getId()).setForce(true);
    }

    public String getProperty(String property) {
       return getPropsAsMap().get(property);
    }
}
