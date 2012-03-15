package edu.mit.cci.turksnet.web;


import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.util.TestException;
import edu.mit.cci.turksnet.util.U;
import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RooWebScaffold(path = "session_s", formBackingObject = Session_.class)
@RequestMapping("/session_s")
@Controller
public class Session_Controller {

    private static Logger log = Logger.getLogger(Session_Controller.class);


    //for the html app

    @RequestMapping(value = "/{id}/turk/feedback", method = RequestMethod.GET)
    public String getForm(@PathVariable("id") Long id, @RequestParam("assignmentId") String assignmentId, @RequestParam("workerid") String workerid, Model model) throws TestException {

        model.addAttribute("workerid", workerid);
        model.addAttribute("assignmentId", assignmentId);
        model.addAttribute("submission", false);

        return "session_s/node/feedback";
    }


    @RequestMapping(value = "/{id}/message", method = RequestMethod.POST)
      @ResponseBody
    public String postFeedback(@PathVariable("id") Long id, @RequestParam("workerid") String workerid, @RequestParam("message") String feedback, Model model) {
        StringBuilder builder = new StringBuilder();
        builder.append("Worker: ").append(workerid).append("\n");
        builder.append("Experiment:").append(Session_.findSession_(id).getExperiment().getId()).append("\n");
        builder.append("Session: ").append(id).append("\n");

        builder.append("Message:\n\n");
        builder.append(feedback);
        log.warn("Worker sent message:\n\n"+builder.toString());

        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        SimpleMailMessage tmpl = context.getBean("mailMessage", SimpleMailMessage.class);
        JavaMailSender sender = context.getBean("mailSender", JavaMailSenderImpl.class);
        SimpleMailMessage message = new SimpleMailMessage(tmpl);
        message.setSentDate(new Date());
        message.setText(builder.toString());
        sender.send(message);
         return "{\"status\":\"ok\"}";
    }

    @RequestMapping(value = "/{id}/application", method = RequestMethod.GET)
    public String getApplication(@PathVariable("id") Long id, Model model, @RequestParam("workerid") Long workerid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        //@TODO better handling of invalid attempt to retrieve application


        Worker w = Worker.findWorker(workerid);
        if (w == null) {
            throw new IllegalArgumentException("Could not identify worker; please login");
        }
        Session_ session = Session_.findSession_(id);
        Node n = Node.findNodesByWorkerAndSession_(w, session).getSingleResult();
        if (n == null) {
            throw new IllegalArgumentException("Worker could not be resolved to a node");
        }

        Experiment exp = session.getExperiment();
        Plugin p = exp.getActualPlugin();
        JSONObject nodedata = n.getJsonData();
        for (Iterator k = nodedata.keys(); k.hasNext(); ) {
            try {
                String key = (String) k.next();
                model.addAttribute(key, U.safejson(nodedata.get(key)));
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        String app = "Error retrieving application";

        try {
            app = p.getApplicationBody(n);
        } catch (Exception e) {
            log.error(e);
        }
        //@TODO fixme - this is a pretty bad hack.  need to figure out fixes
        model.addAttribute("experimentid",session.getExperiment().getId());
        model.addAttribute("sessionid", session.getId());
        model.addAttribute("appData", app.replace("${flash_lib_dir}", "/turksnet/resources/flash/").replace("${timestamp}",System.currentTimeMillis()+""));
        model.addAttribute("workerid",workerid);
        model.addAttribute("numTurns",session.getProperty(Plugin.PROP_ITERATION_COUNT).toString()   );

        return "session_s/app";
    }

    @RequestMapping(value = "/{id}/nodedata", method = RequestMethod.GET)
    @ResponseBody
    public String getDataForNode(@PathVariable("id") Long id, @RequestParam("workerid") Long workerid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        //TODO do a better job with error handling here

        Worker w = Worker.findWorker(workerid);

        if (w == null) {
            throw new IllegalArgumentException("Could not identify worker; please login");
        }
        Session_ session = Session_.findSession_(id);
        Node n = Node.findNodesByWorkerAndSession_(w, session).getSingleResult();
        if (n == null) {
            throw new IllegalArgumentException("Worker could not be resolved to a node");
        }


        String result = U.safejson(n.getJsonData());
        log.debug("Sending " + result + " to " + w.getUsername());
        return result;

    }

    @RequestMapping(value = "/{id}/score", method = RequestMethod.GET)
    @ResponseBody
    public String getScoreForNode(@PathVariable("id") Long id, @RequestParam("workerid") Long workerid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        //TODO do a better job with error handling here
        Worker w = Worker.findWorker(workerid);

        if (w == null) {
            throw new IllegalArgumentException("Could not identify worker; please login");
        }
        log.debug("Received score request from "+ w.getUsername());
        Session_ session = Session_.findSession_(id);
        Node n = Node.findNodesByWorkerAndSession_(w, session).getSingleResult();
        if (n == null) {
            throw new IllegalArgumentException("Worker could not be resolved to a node");
        }
        JSONObject reply = new JSONObject();

        try {
            Map<String,Object> result = null;
            if (session.getRunner().isRunning()) {
                result =  session.getExperiment().getActualPlugin().getScoreInformation(n);
            } else {
                result =  session.getExperiment().getActualPlugin().getFinalInfo(n);

            }

            for (Map.Entry<String, Object> ent : result.entrySet()) {
                reply.put(ent.getKey(), ent.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        String result = U.safejson(reply);
        log.debug("Sending " + result + " to " + w.getUsername());
        return result;

    }


    @RequestMapping(value = "/{id}/nodedata", method = RequestMethod.POST)
    @ResponseBody
    public String postResults(@PathVariable("id") Long id, Model model, @RequestParam("workerid") Long workerid, @RequestParam("data") String data) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, JSONException {


        Worker w = Worker.findWorker(workerid);

        Session_ session = Session_.findSession_(id);
        Node n = Node.findNodesByWorkerAndSession_(w, session).getSingleResult();
        if (n == null) {
            throw new IllegalArgumentException("Worker could not be resolved to a node");
        }


        log.debug("Received " + data + " from " + w.getUsername());
        session.getRunner().updateNode(n, data);
        return "{\"status\"=\"ok\"}";

    }


    @RequestMapping(value = "/{id}/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping(@PathVariable("id") Long id,  @RequestParam("workerid") Long workerid) {
        Worker w = Worker.findWorker(workerid);
        Session_ s = Session_.findSession_(id);
        Map<String, Object> result = new HashMap<String, Object>();

        if (w == null) {
            result.put("status", "http_session_unavailable");

        } else {

            result = s.getRunner().ping(w.getId());
        }


        return U.safejson(new JSONObject(result));


    }

    @RequestMapping(value = "/{id}/run", method = RequestMethod.POST)
    public String run(@PathVariable("id") Long id, Model model, HttpServletRequest request) {

        Session_ session = Session_.findSession_(id);
        try {

            session.run();
        } catch (Exception e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.err.println("Could not run session!");
            return "redirect:/session_s/" + encodeUrlPathSegment(id.toString(), request);

        }

        return "redirect:/session_s/" + encodeUrlPathSegment(session.getId().toString(), request);
    }

    @RequestMapping(value = "/{id}/halt", method = RequestMethod.POST)
    public String halt(@PathVariable("id") Long id, Model model, HttpServletRequest request) {

        Session_ session = Session_.findSession_(id);
        try {

            session.halt();
        } catch (Exception e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.err.println("Could not halt session!");
            return "redirect:/session_s/" + encodeUrlPathSegment(id.toString(), request);

        }

        return "redirect:/session_s/" + encodeUrlPathSegment(session.getId().toString(), request);
    }

     @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return "{\"status\":\"error\"}";
    }





}
