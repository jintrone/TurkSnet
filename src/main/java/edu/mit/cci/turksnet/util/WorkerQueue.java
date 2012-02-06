package edu.mit.cci.turksnet.util;

import edu.mit.cci.turksnet.Worker;

import java.util.Collection;
import java.util.List;

/**
 * User: jintrone
 * Date: 2/1/12
 * Time: 6:29 PM
 */
public interface WorkerQueue {

    public void checkin(Long workerid, boolean prune);
    public int countAvailable(long timeout,boolean prune);
    public Collection<Worker> getAvailable(long timeout, boolean prune);
    public void remove(List<Worker> workers);
    public boolean isActive(long workerid, long timeout);
    public void clear();
}
