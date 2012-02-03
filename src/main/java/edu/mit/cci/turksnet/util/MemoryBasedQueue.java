package edu.mit.cci.turksnet.util;

import edu.mit.cci.turksnet.Worker;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: jintrone
 * Date: 2/1/12
 * Time: 6:32 PM
 */
public class MemoryBasedQueue implements WorkerQueue {


    private final MyLinkedList<Long[]> checkins = new MyLinkedList<Long[]>();
    private final Map<Long, MyLinkedList.Entry<Long[]>> index = new HashMap<Long, MyLinkedList.Entry<Long[]>>();
    private static Logger log = Logger.getLogger(MemoryBasedQueue.class);

    @Override
    public void checkin(Long workerid, boolean prune) {


        synchronized (checkins) {
            MyLinkedList.Entry<Long[]> e = index.get(workerid);
            if (e != null) {
                checkins.removeSpecial(e);
            }
            index.put(workerid, checkins.addSpecial(new Long[]{workerid, System.currentTimeMillis()}));
        }

    }

    public boolean isActive(long workerid, long timeout) {
        MyLinkedList.Entry<Long[]> e = index.get(workerid);
        if (e != null) {
            return e.element[1] >= System.currentTimeMillis() - timeout;
        }
        return false;
    }


    @Override
    public int countAvailable(long timeout, boolean prune) {
        if (prune) return countAvailableDestructive(timeout);
        long previous = System.currentTimeMillis() - timeout;
        int mark = 0;
        int result = 0;
        synchronized (checkins) {
            for (int i = 0; i < checkins.size(); i++) {
                //if (checkins.get(i)==null) continue;
                if (checkins.get(i)[1] < previous) {
                    mark++;
                } else {
                    break;
                }
            }
            result = checkins.size() - mark;
        }
        return result;
    }

    private int countAvailableDestructive(long timeout) {
        long previous = System.currentTimeMillis() - timeout;
        int available = 0;

        synchronized (checkins) {
            for (Iterator<Long[]> l = checkins.iterator(); l.hasNext(); ) {
                Long[] item = l.next();
                if (item[1] < previous) {
                    l.remove();
                    index.remove(item[0]);
                } else {
                    break;
                }
            }

            available = checkins.size();
        }
        return available;
    }

    @Override
    public Collection<Worker> getAvailable(long timeout, boolean prune) {
        Set<Worker> available = new HashSet<Worker>();
        synchronized (checkins) {
            int max = countAvailable(timeout, prune);
            log.debug("Sending "+max+" workers to be assigned");
            for (Long[] l:checkins) {
                Worker w = Worker.findWorker(l[0]);
                if (available.contains(w)) {
                    log.warn("Checkin list has non-unique worker!!!");
                    continue;
                }
                available.add(w);
            }

        }
        return available;
    }

    public void clear() {
        synchronized (checkins) {
            index.clear();
            checkins.clear();
        }
    }

    public void remove(List<Worker> available) {
        synchronized (checkins) {
            for (Worker w : available) {
                MyLinkedList.Entry<Long[]> ent = index.remove(w.getId());
                if (ent != null) {
                    checkins.removeSpecial(ent);

                }

            }
        }
    }
}
