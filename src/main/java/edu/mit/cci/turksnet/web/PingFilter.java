package edu.mit.cci.turksnet.web;


import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.util.U;

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
import java.util.regex.Pattern;

public class PingFilter implements Filter {



    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().matches("/turksnet/experiments/\\d/ping") && Experiment.waitingRoomManager != null) {
            Map<String, Object> result = Experiment.waitingRoomManager.checkin(Long.parseLong(req.getParameter("workerId")));
            String response = U.safejson(result);
            PrintWriter w = res.getWriter();
            w.print(response);
            w.close();
            //res.flushBuffer();

        } else {

            chain.doFilter(req, res);
        }

    }

    public void init(FilterConfig config) throws ServletException {


    }

    public void destroy() {
        //add code to release any resource
    }
}
