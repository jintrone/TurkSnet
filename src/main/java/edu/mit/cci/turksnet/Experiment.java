package edu.mit.cci.turksnet;

import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turksnet.constraints.ValidClass;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.plugins.SessionCreationException;
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
@RooToString()
@RooEntity
public class Experiment {

    @Column(columnDefinition = "LONGTEXT")
    private String properties;


    @Column(columnDefinition = "LONGTEXT")
    private String network;

    @Column(columnDefinition="LONGTEXT")
    private String javaScript;

    @ValidClass
    private String pluginClazz;

    @Transient
    private static Plugin actual;

    @Transient
    private Map<String,String> propsAsMap;

    public Plugin getActualPlugin() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (actual == null) {
             actual = (Plugin) Class.forName(pluginClazz).newInstance();
        }
        return actual;
    }

    public Session_ createSession() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SessionCreationException {
        Plugin p = getActualPlugin();
        return p.createSession(this);

    }

    public List<Session_> getSessions() {
       List<Session_> result = new ArrayList<Session_>(Session_.findAllSession_s());
       for (Iterator<Session_> i = result.iterator();i.hasNext();) {
           if (i.next().getExperiment().equals(this)) continue;
           i.remove();

       }
        return result;
    }

    public Map<String,String> getPropsAsMap() {
        if (propsAsMap == null) {
            propsAsMap = new HashMap<String, String>();
            propsAsMap.putAll(U.splitProperties(getProperties()));
        }
        return propsAsMap;
    }

     public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties: ").append(getProperties()).append(", ");
        sb.append("Network: ").append(getNetwork()).append(", ");
        sb.append("PluginClazz: ").append(getPluginClazz());
        return sb.toString();
    }


}
