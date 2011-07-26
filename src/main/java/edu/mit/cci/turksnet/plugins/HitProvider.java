package edu.mit.cci.turksnet.plugins;

import apple.laf.JRSUIConstants;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;

/**
 * User: jintrone
 * Date: 2/17/11
 * Time: 2:43 PM
 */
public interface HitProvider {


    public String getHitView(Node hit, String turker);

}
