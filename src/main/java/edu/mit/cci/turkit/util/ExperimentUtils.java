package edu.mit.cci.turkit.util;

import edu.mit.cci.turksnet.Session_;

import java.util.Date;

/**
 * User: jintrone
 * Date: 6/30/11
 * Time: 7:11 AM
 */
public class ExperimentUtils {

    public ExperimentUtils() {}

    public static Session_ createSession(int id) {
       Session_ result =  new Session_(id);
        result.setCreated(new Date());
        System.err.println("Session is "+result);
        return result;
    }

    public static long getLong() {
        return 1l;
    }

}
