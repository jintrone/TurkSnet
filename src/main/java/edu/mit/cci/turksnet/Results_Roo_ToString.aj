// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet;

import java.lang.String;

privileged aspect Results_Roo_ToString {
    
    public String Results.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Session_: ").append(getSession_()).append(", ");
        sb.append("Node: ").append(getNode()).append(", ");
        sb.append("Received: ").append(getReceived()).append(", ");
        sb.append("TurkerId: ").append(getTurkerId()).append(", ");
        sb.append("ResultBody: ").append(getResultBody()).append(", ");
        sb.append("Iteration: ").append(getIteration());
        return sb.toString();
    }
    
}
