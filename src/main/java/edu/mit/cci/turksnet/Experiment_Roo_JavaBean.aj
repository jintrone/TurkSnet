// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet;

import java.lang.Boolean;
import java.lang.String;
import java.util.Map;

privileged aspect Experiment_Roo_JavaBean {
    
    public String Experiment.getProperties() {
        return this.properties;
    }
    
    public void Experiment.setProperties(String properties) {
        this.properties = properties;
    }
    
    public String Experiment.getNetwork() {
        return this.network;
    }
    
    public void Experiment.setNetwork(String network) {
        this.network = network;
    }
    
    public String Experiment.getJavaScript() {
        return this.javaScript;
    }
    
    public void Experiment.setJavaScript(String javaScript) {
        this.javaScript = javaScript;
    }
    
    public String Experiment.getPluginClazz() {
        return this.pluginClazz;
    }
    
    public void Experiment.setPluginClazz(String pluginClazz) {
        this.pluginClazz = pluginClazz;
    }
    
    public void Experiment.setPropsAsMap(Map<String, String> propsAsMap) {
        this.propsAsMap = propsAsMap;
    }
    
    public Boolean Experiment.getRunning() {
        return this.running;
    }
    
    public void Experiment.setRunning(Boolean running) {
        this.running = running;
    }
    
}
