package edu.mit.cci.turksnet.util;

import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;

import java.util.*;

/**
 * User: jintrone
 * Date: 2/1/12
 * Time: 6:32 PM
 */
public class MemoryBasedQueue  implements WorkerQueue {


    private final MyLinkedList<Long[]> checkins = new MyLinkedList<Long[]>();
    private final Map<Long,MyLinkedList.Entry<Long[]>> index = new HashMap<Long,MyLinkedList.Entry<Long[]>>();

    @Override
    public void checkin(Worker w, boolean prune) {


        synchronized (checkins) {
            MyLinkedList.Entry<Long[]> e = index.get(w.getId());
            if (e!=null) {
                checkins.removeSpecial(e);
            }
            index.put(w.getId(), checkins.addSpecial(new Long[]{w.getId(), System.currentTimeMillis()}));
        }

    }



    @Override
    public int countAvailable(long timeout, boolean prune) {
        if (prune) return countAvailableDestructive(timeout);
        long previous = System.currentTimeMillis() - timeout;
        int mark = 0;
        for (int i = 0;i<checkins.size();i++) {
            if (checkins.get(i)[1] < previous) {
                mark++;
            } else {
                break;
            }
        }
        return checkins.size() - mark;
    }

    private int countAvailableDestructive(long timeout) {
        long previous = System.currentTimeMillis() - timeout;
        int available = 0;

        synchronized (checkins) {
            for (Iterator<Long[]> l=checkins.iterator();l.hasNext();) {
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
    public List<Worker> getAvailable(long timeout, boolean prune) {
            int max = countAvailable(timeout,prune);
            List<Worker> available = new ArrayList<Worker>(max);
            for (int i = checkins.size()-1;max>0;i--,max--) {
                available.add(Worker.findWorker(checkins.get(i)[0]));
                if (max ==0) break;
            }
            return available;
    }

    public void remove(List<Worker> available) {
        synchronized (checkins) {
            for (Worker w:available) {
                MyLinkedList.Entry<Long[]> ent = index.get(w.getId());
                if (ent!=null) {
                   checkins.removeSpecial(ent);
                }

            }
        }
    }
}
