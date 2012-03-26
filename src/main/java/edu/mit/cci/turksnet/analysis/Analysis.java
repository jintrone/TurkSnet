package edu.mit.cci.turksnet.analysis;

import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.SessionLog;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.LoomStandalonePlugin;
import edu.mit.cci.turksnet.plugins.Plugin;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jintrone
 * Date: 1/26/12
 * Time: 9:35 AM
 */


public class Analysis {


    public void analyzeWorkers(Filter<Worker> f, PrintStream stream) {
        List<Worker> workers = new ArrayList<Worker>(Worker.findAllWorkers());
        List<Integer> training_truth = new ArrayList<Integer>();

        for (int i = 1; i <= 10; i++) {
            training_truth.add(i);
        }

        for (Iterator<Worker> i = workers.iterator(); i.hasNext(); ) {
            if (!f.accept(i.next())) i.remove();
        }

        float max_score = 0;
        max_score += LoomStandalonePlugin.score(training_truth, Arrays.asList(1, 3, 5, 6, 7, 9));
        max_score += LoomStandalonePlugin.score(training_truth, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9));
        max_score += LoomStandalonePlugin.score(training_truth, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Max score is " + max_score);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Worker w : workers) {
            Map<String, String> row = new LinkedHashMap<String, String>();
            data.add(row);
            row.put("name", w.getUsername());
            row.put("status", "1_LOGGED_IN");
            if (w.getQualifications() != null) {
                row.put("status", "2_INFO");
            }
            if (w.getTraining() != null) {
                row.put("status", "3_START_TRAINING");
                String[] trainingdata = w.getTraining().split("&");
                if (trainingdata.length == 3) {
                    row.put("status", "4_FINISH_TRAINING");
                    float score = 0;
                    float count = 0;
                    for (String turn : trainingdata) {
                        String[] turn_s = turn.split(";");
                        count += turn_s.length;
                        List<Integer> turn_sol = new ArrayList<Integer>();
                        for (String tile : turn_s) {
                            turn_sol.add(Integer.parseInt(tile));
                        }
                        if (!turn_sol.isEmpty()) {
                            System.out.println("Checking " + training_truth + " against " + turn_sol);
                            score += LoomStandalonePlugin.score(training_truth, turn_sol);
                        }
                        row.put("engagement", count + "");
                        row.put("score", score + "");
                    }

                }
            }
        }

        dump(Arrays.asList("name", "status", "engagement", "score"), data, stream);
    }

    public static void findWorkers(long... w) {
        for (long id : w) {
            System.out.println(Worker.findWorker(id).getUsername());
        }
    }


    public static void dump(List<String> headers, List<Map<String, String>> data, PrintStream output) {

        StringBuilder b = new StringBuilder();
        String sep = "";
        if (headers == null) {
            headers = new ArrayList<String>(data.get(0).keySet());
        }
        for (String col : headers) {
            b.append(sep).append(col);
            sep = ",";
        }

        b.append("\n");

        for (Map<String, String> m : data) {
            sep = "";
            for (String key : headers) {
                b.append(sep).append(m.get(key));
                sep = ",";
            }
            b.append("\n");
        }

        output.println(b.toString());
    }

    private interface Filter<T> {
        public boolean accept(T object);
    }

    @Transactional
    public void dumpSessionScores() throws FileNotFoundException {
//        for (Worker w : Worker.findAllWorkers()) {
//            System.err.println("Worker: " + w);
//        }

        LoomStandalonePlugin plugin = new LoomStandalonePlugin();
        List<String> headers = new ArrayList<String>(Arrays.asList("Worker","Active"));
        for (int i=0;i<13;i++) {
            headers.add("Step"+i);
        }

        for (Session_ s : Session_.findAllSession_s()) {

            //System.err.println("Session "+s.getProperty(Plugin.PROP_SESSION_ID));
            PrintStream out = new PrintStream(new FileOutputStream("Session "+s.getProperty(Plugin.PROP_SESSION_ID)+".csv"));
            List<Map<String,String>> csv = new ArrayList<Map<String, String>>();
            for (Node n : s.getNodesAsList()) {
                Map<String,String> row = new HashMap<String, String>();
                csv.add(row);
                row.put("Worker",n.getWorker().getUsername());
                List<SessionLog> logs = new ArrayList<SessionLog>();
                List<Date> dates = new ArrayList<Date>();
                List<String> statuses = new ArrayList<String>();
                float status = 0.0f;
                boolean flagged = false;
                for (SessionLog log : SessionLog.findAllSessionLogs()) {


                    if (n.getId().equals(log.getNode().getId())) {
                        if (log.getType().equals("results")) {
                            logs.add(log);
                            dates.add(log.getDate_());
                            if (flagged) {
                                //automated
                                statuses.add("AUTO");
                                flagged = false;

                            } else {
                                statuses.add("OK");
                                status+=(1.0/13.0);
                            }

                        } else if (log.getType().equals("timeout")) {
                            flagged = true;
                        }


                    }

                }
                row.put("Active",status+"");

                List<Float> scores = plugin.getSessionScores(logs);
               // List<Float> datause = getDataUse(logs);
                System.err.println(n.getWorker().getUsername() + "\n**************");
                for (int i = 0; i < scores.size(); i++) {
                    //row.put("Step"+i,scores.get(i)+"");
                    System.err.println(dates.get(i)+":"+statuses.get(i) + " -- " + scores.get(i));

                }
            }
            //dump(headers,csv,out);
           // out.close();

        }
    }

//    public List<Float> getDataUse(List<SessionLog> logs) {
//
//    }
//
//    public Float getDataUse(SessionLog log) {
//        log.getNodePublicData();
//        log.getNode().getIncoming();
//    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setProperty("database.url", "jdbc:mysql://localhost:3306/turksnet-cognosis");
        Logger.getLogger(LoomStandalonePlugin.class).setLevel(Level.OFF);
        System.err.println("Set database prop to " + System.getProperty("database.url"));


        new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
//        new Analysis().analyzeWorkers(new Filter<Worker>() {
//            @Override
//            public boolean accept(Worker object) {
//                return object.getId() >= 258;
//            }
//        }, new PrintStream(System.out));

        new Analysis().dumpSessionScores();

    }
}
