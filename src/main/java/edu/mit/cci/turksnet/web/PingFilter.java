package edu.mit.cci.turksnet.web;


import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.util.RunStrategy;
import edu.mit.cci.turksnet.util.U;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONObject;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingFilter implements Filter {


    private static Logger log = Logger.getLogger(PingFilter.class);

    Pattern p = Pattern.compile("/turksnet/(experiments|session_s)/(\\d+)/ping");

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        Matcher m = p.matcher(request.getRequestURI());

        if (m.matches()) {

            if (m.group(1).equals("experiments") && Experiment.waitingRoomManager != null) {
                long wid = Long.parseLong(request.getParameter("workerId"));
                Worker wrker = Worker.findWorker(wid);
                if (wrker.getCurrentAssignment() == null) {
                    Map<String, Object> result = Experiment.waitingRoomManager.checkin(wid);
                    result.put("status", "waiting");
                    String response = U.safejson(new JSONObject(result));
                    PrintWriter w = res.getWriter();
                    log.debug("Waiting room ping response: " + response);
                    w.print(response);
                    w.close();
                    res.flushBuffer();
                    return;
                }
            } else if (m.group(1).equals("session_s")) {

                RunStrategy strategy = Session_.lookupRunStrategy(Long.parseLong(m.group(2)));

                String response = null;
                if (strategy == null) {
                    response = "{\"status\":\"http_session_unavailable\"}";
                } else if (strategy.getGameState() != RunStrategy.GameState.DONE_GAME) {
                    Map<String, Object> result = strategy.ping(Long.parseLong(request.getParameter("workerid")));
                    response = U.safejson(new JSONObject(result));
                }
                if (response != null) {
                    PrintWriter w = res.getWriter();
                    log.debug("Session ping response: " + response);
                    w.print(response);
                    w.close();
                    return;
                }

            }


        }

        chain.doFilter(req, res);


    }

    public void init(FilterConfig config) throws ServletException {


    }

    public void destroy() {
        //add code to release any resource
    }
}
