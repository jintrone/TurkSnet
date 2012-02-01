package edu.mit.cci.turksnet.analysis;

import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.SessionLog;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.LoomStandalonePlugin;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jintrone
 * Date: 1/26/12
 * Time: 9:35 AM
 */


public class Analysis {

    public static void main(String[] args) {
        System.setProperty("database.url", "jdbc:mysql://localhost:3306/turksnet-cognosis");
        Logger.getLogger(LoomStandalonePlugin.class).setLevel(Level.OFF);
        System.err.println("Set database prop to " + System.getProperty("database.url"));
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
        for (Worker w : Worker.findAllWorkers()) {
            System.err.println("Worker: " + w);
        }

        LoomStandalonePlugin plugin = new LoomStandalonePlugin();
        for (Node n : Node.findAllNodes()) {
            List<SessionLog> logs = new ArrayList<SessionLog>();
            List<String> statuses = new ArrayList<String>();
            boolean flagged = false;
            for (SessionLog log : SessionLog.findAllSessionLogs()) {


                if (n.getId().equals(log.getNode().getId())) {
                    if (log.getType().equals("results")) {
                        logs.add(log);
                        if (flagged) {
                            flagged = false;
                            statuses.add(log.getDate_()+" : AUTOMATED");
                        } else {
                            statuses.add(log.getDate_()+" : OK");
                        }

                    } else if (log.getType().equals("timeout")) {
                        flagged = true;
                    }


                }

            }
            List<Float> scores = plugin.getSessionScores(n.getSession_().getExperiment(), logs);

            System.err.println(n.getWorker().getUsername() + "\n**************");
            for (int i = 0;i<scores.size();i++) {
                System.err.println(statuses.get(i)+" -- "+scores.get(i));

            }

        }
    }
}
