// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet;

import java.lang.String;

privileged aspect Session__Roo_ToString {
    
    public String Session_.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PropertiesAsMap: ").append(getPropertiesAsMap() == null ? "null" : getPropertiesAsMap().size()).append(", ");
        sb.append("Log: ").append(getLog()).append(", ");
        sb.append("Network: ").append(getNetwork()).append(", ");
        sb.append("Created: ").append(getCreated()).append(", ");
        sb.append("Active: ").append(getActive()).append(", ");
        sb.append("Experiment: ").append(getExperiment()).append(", ");
        sb.append("Iteration: ").append(getIteration()).append(", ");
        sb.append("PendingNodes: ").append(getPendingNodes() == null ? "null" : getPendingNodes().size()).append(", ");
        sb.append("AvailableNodes: ").append(getAvailableNodes() == null ? "null" : getAvailableNodes().size()).append(", ");
        sb.append("OutputLog: ").append(getOutputLog()).append(", ");
        sb.append("Properties: ").append(getProperties()).append(", ");
        sb.append("Format: ").append(getFormat()).append(", ");
        sb.append("Runner: ").append(getRunner()).append(", ");
        sb.append("QualificationRequirements: ").append(getQualificationRequirements());
        return sb.toString();
    }
    
}
