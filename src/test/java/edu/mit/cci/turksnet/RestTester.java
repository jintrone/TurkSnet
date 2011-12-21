package edu.mit.cci.turksnet;


import edu.mit.cci.turkit.util.U;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONObject;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        URL u = new URL("http://localhost:8084/turksnet/experiments/29/register");
        String result = U.webPost(u, "login", "t1", "password", "\"\"");
        JSONObject obj = new JSONObject(result);


        for (int i = 0; i < 65; i++) {
            final String w = "test-robustness" + i;


            Thread t = new Thread(new WorkerRunner(w));
            t.start();
            Thread.sleep((long) (Math.random()*1000l));

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
                System.err.println("Execute " + name);
                URL u = null;

                u = new URL("http://localhost:8084/turksnet/experiments/29/register");

                String result = U.webPost(u, "login", name, "password", "\"\"");
                JSONObject obj = new JSONObject(result);

                if (obj.getString("status").equals("username_exists")) {
                    u = new URL("http://localhost:8084/turksnet/experiments/29/login");
                    result = U.webPost(u, "login", name, "password", "\"\"");
                    obj = new JSONObject(result);

                }
                Long id = obj.getLong("workerid");
                log.debug(name + " -- register:" + result);

                u = new URL("http://localhost:8084/turksnet/experiments/29/ping?workerid=" + id);

                for (int i = 0; i < 1000; i++) {
                    System.err.println("I - "+i);
                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                    connection.addRequestProperty("accept", "text/json");
                    connection.connect();
                    result = U.slurp(connection.getInputStream(), "UTF-8");
                    connection.disconnect();
                    log.debug(i + "." + name + " -- ping:" + result);

                    Thread.sleep((long) (Math.random()*1000l));


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
