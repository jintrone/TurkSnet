package edu.mit.cci.turksnet;



import edu.mit.cci.turkit.util.U;
import org.apache.log4j.Logger;
import org.junit.Test;


import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
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
        connection.addRequestProperty("accept","text/xml");
        connection.connect();
        String result = U.slurp(connection.getInputStream(), "UTF-8");
        connection.disconnect();
        System.err.println("Result is "+result);
        log.debug(result);

        U.webPost(u,"data","fooey");


    }
}
