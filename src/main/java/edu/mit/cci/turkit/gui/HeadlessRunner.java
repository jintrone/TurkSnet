package edu.mit.cci.turkit.gui;

import edu.mit.cci.turkit.turkitBridge.TurKit;
import edu.mit.cci.turkit.util.InvalidPropertiesException;
import edu.mit.cci.turkit.util.NamedSource;
import edu.mit.cci.turkit.util.TurkitOutputSink;
import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turksnet.Session_;
import org.apache.log4j.Logger;


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
public class HeadlessRunner {
    public static JavaScriptDatabase turkitProperties;
    // public SimpleEventManager sem;

    private static Logger log = Logger.getLogger(HeadlessRunner.class);


    public TurKit turkit;
    public NamedSource jsSource;


    public long runAgainAtThisTime;
    public Timer timer;

    public Map<String, String> properties;

    public TurkitOutputSink sink;

    private final ExecutorService service;

    public HeadlessRunner(TurkitOutputSink sink) throws Exception {
        this.sink = sink;
        this.service = Executors.newSingleThreadExecutor();
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

        this.sink = sink;
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
                            System.err.println("Timer run, context is " + org.mozilla.javascript.Context.getCurrentContext());
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


    public void run(final boolean repeat) {
        service.submit(new Runnable() {
            @Override
            public void run() {
               _run(repeat);

            }
        });
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


        if (repeat && !done) {
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
        deferRun();
    }


}
