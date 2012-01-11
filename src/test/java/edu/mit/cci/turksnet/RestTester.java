package edu.mit.cci.turksnet;


import edu.mit.cci.turkit.util.U;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: jintrone
 * Date: 7/21/11
 * Time: 1:05 PM
 */
public class RestTester {

    private static Logger log = Logger.getLogger(RestTester.class);

    @Test
    public void testService() throws Exception {
        URL u = new URL("http://localhost:8080/turksnet/session_s/1/turk/someturker");
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.addRequestProperty("accept", "text/xml");
        connection.connect();
        String result = U.slurp(connection.getInputStream(), "UTF-8");
        connection.disconnect();
        System.err.println("Result is " + result);
        log.debug(result);

        U.webPost(u, "data", "fooey");


    }

    @Test
    public void testRobustness() throws Exception {

        for (int i = 0; i < 50; i++) {
            final String w = "test-robustness" + i;


            Thread t = new Thread(new WorkerRunner(w));
            t.start();
            Thread.sleep((long) (Math.random() * 500l));

        }

    }


    public void runWorker(String s) throws Exception {

    }


    public void logonProcedure() {
        //URL register = new URL("http://localhost:8084/turksnet/experiment/1/")

    }

    public static class WorkerRunner implements Runnable {

        String name;

        public WorkerRunner(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                log.debug("Starting " + name);
                URL u = null;

                u = new URL("http://cognosis.mit.edu:8084/turksnet/experiments/1/register");

                String result = U.webPost(u, "login", name, "password", "\"\"");
                log.debug("Raw result: " + result);
                JSONObject obj = new JSONObject(result);

                if (obj.getString("status").equals("username_exists")) {
                    u = new URL("http://cognosis:8084/turksnet/experiments/1/login");
                    result = U.webPost(u, "login", name, "password", "\"\"");
                    obj = new JSONObject(result);

                }
                final Long workerid = obj.getLong("workerid");
                log.debug(name + " -- register:" + result);

                u = new URL("http://cognosis.mit.edu:8084/turksnet/experiments/1/ping?workerid=" + workerid);

                String sessionendpoint = "";

                while (true) {

                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                    connection.addRequestProperty("accept", "text/json");

                    connection.connect();
                    result = U.slurp(connection.getInputStream(), "UTF-8");
                    connection.disconnect();
                    obj = new JSONObject(result);
                    if (obj.getString("status").equals("worker_assigned")) {
                        Pattern p = Pattern.compile("session_s/([^/]+)/.*$");
                        Matcher m = p.matcher(obj.getString("session_url"));
                        if (!m.matches()) {
                            throw new RuntimeException("Should have matched session url");
                        } else sessionendpoint = m.group(1);
                        break;
                    }
                    Thread.sleep(500l);
                }

                final String pingurl = String.format("http://cognosis.mit.edu:8084/turksnet/session_s/%s/ping?workerid=%d", sessionendpoint, workerid.intValue());
                final String submiturl = String.format("http://cognosis.mit.edu:8084/turksnet/session_s/%s/nodedata", sessionendpoint);
                final String dataurl = String.format("http://cognosis.mit.edu:8084/turksnet/session_s/%s/nodedata?workerid=%d", sessionendpoint, workerid.intValue());
                final StringBuffer buf = new StringBuffer();



                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(dataurl).openConnection();
                    connection.addRequestProperty("accept", "text/json");
                    connection.connect();
                    result = U.slurp(connection.getInputStream(), "UTF-8");
                    connection.disconnect();
                    obj = new JSONObject(result);
                    JSONArray ary = obj.getJSONArray("publicData");

                    for (int i = 0; i < ary.length(); i++) {
                        buf.append(ary.getJSONObject(i).keys().next()).append(";");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                log.debug("URL:" + pingurl);

                while (true) {
                    final boolean[] submit = {false};
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                URL u = new URL(pingurl);
                                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                                connection.addRequestProperty("accept", "text/json");
                                connection.connect();
                                String result = U.slurp(connection.getInputStream(), "UTF-8");
                                connection.disconnect();
                                JSONObject obj = new JSONObject(result);
                                log.debug("Worker " + name + " : " + obj.getString("status"));
                                if (obj.getString("status").equals("WAITING_FOR_RESULTS")) {
                                    submit[0] = true;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }).run();
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                URL u = new URL(pingurl);
                                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                                connection.addRequestProperty("accept", "text/json");
                                connection.connect();
                                String result = U.slurp(connection.getInputStream(), "UTF-8");
                                connection.disconnect();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }).run();

                    if (submit[0]) {
                        submit[0] = false;
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    URL u = new URL(submiturl);

                                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                                    log.debug("Worker "+workerid+" submit: "+buf.toString());

                                   String result = U.webPost(connection,"workerid",""+workerid,"data",buf.toString());

                                    JSONObject obj = new JSONObject(result);
                                    log.debug("Worker " + name + " : " + obj.getString("status"));
                                    if (obj.getString("status").equals("WAITING_FOR_RESULTS")) {
                                        submit[0] = true;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }).run();
                    }
                   // Thread.sleep(1000l);


                }


            } catch (Throwable t) {
                t.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.err.println("I am outside");
        }
    }

    public static void main(String[] args) throws Exception {
        new RestTester().testRobustness();
    }
}
