package edu.mit.cci.turksnet.util;

import edu.mit.cci.turksnet.Worker;

import java.util.List;

/**
 * User: jintrone
 * Date: 2/1/12
 * Time: 6:29 PM
 */
public interface WorkerQueue {

    public void checkin(Worker w, boolean prune);
    public int countAvailable(long timeout,boolean prune);
    public List<Worker> getAvailable(long timeout,boolean prune);
    public void remove(List<Worker> workers);
}
