package edu.mit.cci.turksnet;



import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User: jintrone
 * Date: 1/20/12
 * Time: 3:14 PM
 */
public class ExperimentRunner extends TimerTask  {

    private static Logger log = Logger.getLogger(ExperimentRunner.class);
    private static Timer timer = null;
    private static List<Long> timedExperiments = Collections.synchronizedList(new ArrayList<Long>());
    private static Comparator<Long> cmp = new Comparator<Long>() {

        @Override
        public int compare(Long aLong, Long aLong1) {
            Date d1 =  Experiment.findExperiment(aLong).getStartDate();
            Date d2 = Experiment.findExperiment(aLong1).getStartDate();
            return d1.compareTo(d2);

        }
    };




    public void start() {

        if (timer == null) {
            timer = new Timer("ClockThread");
            initExperiments();
            log.info("Starting experiment clock timer...");
            timer.scheduleAtFixedRate(this, new Date(), 60 * 1000);
        }
    }

    public synchronized void updateExperiment(Experiment e) {
        Date current = new Date();
        if (e.getRunning() && (e.getStartDate() != null && e.getStartDate().after(current))) {
            log.info("Adding experiment " + e.getId() + " for a future run at "+e.getStartDate());
            if (timedExperiments.contains(e.getId())) {
                timedExperiments.remove(e.getId());
            }
            int idx = Collections.binarySearch(timedExperiments, e.getId(), cmp);
            timedExperiments.add((idx < 0)?(idx+1)*-1:idx, e.getId());
        }
    }

    @Transactional
    private void initExperiments() {
        List<Experiment> es = Experiment.findAllExperiments();

        for (Experiment e : es) {
            updateExperiment(e);
        }
    }

    @Override
    public synchronized void run() {
        Date current = new Date();
        log.info("Checking experiments at "+current);
        for (int i = 0;i<timedExperiments.size();) {
            Experiment e = Experiment.findExperiment(timedExperiments.get(i));
            if (!e.getRunning() || e.getStartDate() == null ) {
                log.info("Pruning old experiment "+e.getId());
                timedExperiments.remove(i);
            } else if (e.getStartDate().compareTo(current)<1) {
                 log.info("Running experiment "+e.getId());
                e.forceRun();
                timedExperiments.remove(i);

            } else {
                i++;
            }
        }


    }
}
