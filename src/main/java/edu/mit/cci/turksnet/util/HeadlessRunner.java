package edu.mit.cci.turksnet.util;

import edu.mit.cci.turkit.gui.JavaScriptDatabase;
import edu.mit.cci.turkit.turkitBridge.TurKit;
import edu.mit.cci.turkit.util.InvalidPropertiesException;
import edu.mit.cci.turkit.util.NamedSource;
import edu.mit.cci.turkit.util.TurkitOutputSink;
import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: jintrone
 * Date: 7/19/11
 * Time: 10:14 PM
 */
public class HeadlessRunner implements ApplicationContextAware, RunStrategy {
    public static JavaScriptDatabase turkitProperties;
    // public SimpleEventManager sem;

    private static Logger log = Logger.getLogger(HeadlessRunner.class);


    public static ApplicationContext context;

    public TurKit turkit;
    public NamedSource jsSource;


    public long runAgainAtThisTime;
    public Timer timer;

    public Map<String, String> properties;

    public TurkitOutputSink sink;

    public boolean forceStop = false;

    public Session_ session;


    private static final ExecutorService service = Executors.newSingleThreadExecutor();

    public HeadlessRunner() {

    }

    public HeadlessRunner(TurkitOutputSink sink) throws Exception {

    }


    public void loadScript(String name, String cmds, Map<String, String> properties, Session_ session) throws Exception {
        //String mode = "offline";
        this.properties = properties;

        //mode = ((String) props.get("mode")).toLowerCase();
        jsSource = new NamedSource.StringSource(cmds, name);
        turkit = new TurKit(jsSource, "", "", "offline");
        turkit.setActiveSession(session);

    }

    public void reinit() throws Exception {
        if (!properties.containsKey("awsAccessKeyID") || !properties.containsKey("awsSecretAccessKey")) {
            throw new InvalidPropertiesException("Properities to aws credentials are required");
        }
        String awsAccessKeyID = (String) properties.get("awsAccessKeyID");
        String awsSecretAccessKey = (String) properties.get("awsSecretAccessKey");
        turkit.reinit(jsSource, awsAccessKeyID, awsSecretAccessKey,
                (String) properties.get("mode"));

        turkitProperties.query("awsAccessKeyID = \""
                + U.escapeString(awsAccessKeyID) + "\";"
                + "awsSecretAccessKey = \""
                + U.escapeString(awsSecretAccessKey) + "\";"
                + "recentFile = \"" + U.escapeString(jsSource.getName())
                + "\"");

    }

    public void runInABit(long delaySeconds) {
        runAgainAtThisTime = System.currentTimeMillis() + (delaySeconds * 1000);
        deferRun();
    }

    public void deferRun() {
        if (runAgainAtThisTime >= 0) {
            long delta = runAgainAtThisTime - System.currentTimeMillis();


            if (timer != null) {
                timer.stop();
            }
            timer = new Timer((int) Math.max(Math.min(delta, 1000), 0),
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {

                            try {
                                if (runAgainAtThisTime < 0) {
                                    //halt
                                    // deferRun();
                                } else {
                                    long delta = runAgainAtThisTime
                                            - System.currentTimeMillis();
                                    if (delta <= 0) {
                                        run(true);
                                    } else {
                                        deferRun();
                                    }
                                }
                            } catch (Exception e) {
                                // print this error to the output pane
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                PrintStream ps = new PrintStream(out);
                                e.printStackTrace(ps);
                                ps.close();
                                String s = out.toString();
                                sink.setText("Unexpected Error:\n" + s);

                                // let's press on
                                runInABit(60);
                            }

                        }
                    });
            timer.setRepeats(false);
            timer.start();
        }
    }


    @Override
    public void init(Session_ session, TurkitOutputSink sink) throws Exception {
        this.sink = sink;
        this.session = session;
        service.submit(new Runnable() {
            public void run() {
                if (turkitProperties == null) {
                    try {
                        turkitProperties = new JavaScriptDatabase(new File(
                                "turkit.properties"), new File("turkit.properties.tmp"));
                    } catch (Exception e) {
                        log.error("Error initializing database");
                        HeadlessRunner.this.sink.startCapture();
                        HeadlessRunner.this.sink.setText(e.getMessage());
                        HeadlessRunner.this.sink.stopCapture();
                        HeadlessRunner.this.service.shutdown();
                        throw new RuntimeException("Could not init headless runner");

                    }

                }
            }
        });
        loadScript("Experiment:" + session.getExperiment().getId() + "_Session:" + session.getId(), session.getExperiment().getJavaScript(), session.getExperiment().getPropsAsMap(), session);

    }

    public void run(final boolean repeat) {

        service.submit(new Runnable() {
            @Override
            @Transactional
            public void run() {
                try {
//

                    _run(repeat);

                } catch (Throwable t) {
                    t.printStackTrace();
                } finally {

                }

            }
        });
    }


    public void haltOnNext() {
        this.forceStop = true;
    }

    public void _run(boolean repeat) {
        //stop();

        boolean done = false;

        sink.startCapture();
        try {

            reinit();
            done = turkit.runOnce(Double.valueOf(properties.get("maxMoney")), Integer.valueOf(properties.get("maxHITs")));
            updateDatabase();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            sink.stopCapture();
        }


        if (repeat && !done && !forceStop) {
            Double o = Double.valueOf(properties.get("repeatInterval"));
            int delay = o != null ? (int) Math.ceil((Double) o) : 60;
            runInABit(delay);
        } else {
            stop();
        }


    }


    private void updateDatabase() throws Exception {
        turkit.database.consolidate();


    }

    public void stop() {
        runAgainAtThisTime = -1;
        forceStop = false;
        deferRun();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public boolean isRunning() {
        return runAgainAtThisTime > -1;
    }


    @Override
    public void updateNode(Node n, String results) throws ClassNotFoundException, IllegalAccessException, InstantiationException, JSONException {



            //logNodeEvent(n, "results");
            n.setStatus(NodeStatus.WAITING.name());
            n.persist();
            log.debug("Set node " + n.getId() + " to not accept");
            synchronized (getClass()) {
                Plugin p =  session.getExperiment().getActualPlugin();
               p.processResults(n, results);
                session.logNodeEvent(n, "results");
                boolean doneiteration = true;
                for (Node node : session.getAvailableNodes()) {
                    log.debug("Making sure " + n.getId() + " is up to date");
                    node.merge();
                    log.debug("Checking node for doneness " + node.getId());
                    if (NodeStatus.valueOf(node.getStatus()) == NodeStatus.ACCEPTING_INPUT) {
                        log.debug("Node is accepting input!");
                        doneiteration = false;
                    } else {

                    }
                    log.debug("Node is NOT accepting input!");
                }
                if (doneiteration) {
                    session.setIteration(session.getIteration() + 1);

                }
                log.debug("Checking for session doneness");
                if (p.checkDone(session)) {
                    log.debug("I am DONE");
                    session.setStatus(Session_.Status.RUNNING);


                }

            }
            session.persist();



    }

    @Override
    public Map<String, Object> ping(Worker w, HttpSession session) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



}
