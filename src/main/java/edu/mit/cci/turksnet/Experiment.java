package edu.mit.cci.turksnet;

import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turksnet.constraints.ValidClass;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.plugins.SessionCreationException;
import edu.mit.cci.turksnet.util.RunStrategy;
import edu.mit.cci.turksnet.util.WaitingRoomManager;
import org.apache.log4j.Logger;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RooJavaBean
@RooEntity
public class Experiment {

    @Column(columnDefinition = "LONGTEXT")
    private String properties;

    @Column(columnDefinition = "LONGTEXT")
    private String network;

    @Column(columnDefinition = "LONGTEXT")
    private String javaScript;

    @ValidClass
    private String pluginClazz;

    @Transient
    private static Plugin actual;

    @Transient
    private Map<String, String> propsAsMap;

    private Boolean running= false;

    @Transient
    private static WaitingRoomManager waitingRoomManager;

    private static Logger log = Logger.getLogger(Experiment.class);


    public Plugin getActualPlugin() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (actual == null) {
            actual = (Plugin) Class.forName(pluginClazz).newInstance();
        }
        return actual;
    }



    public RunStrategy getStrategyInstance() {
        return RunStrategy.Type.valueOf(getPropsAsMap().get(Plugin.PROP_RUN_STRATEGY)).instance();
    }

    public List<Session_> getSessions() {
        List<Session_> result = new ArrayList<Session_>(Session_.findAllSession_s());
        for (Iterator<Session_> i = result.iterator(); i.hasNext(); ) {
            if (i.next().getExperiment().equals(this)) continue;
            i.remove();
        }
        return result;
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

   public void run() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        waitingRoomManager = new WaitingRoomManager(this);
        this.setRunning(true);
       this.persist();

   }

    public WaitingRoomManager getWaitingRoomManager() {
        if (waitingRoomManager == null && this.getRunning()) {
            try {
                log.warn("Waiting room manager not running; trying to restart");
                run();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return waitingRoomManager;
    }


    public void stop() {
        this.setRunning(false);
         this.persist();
    }
}
