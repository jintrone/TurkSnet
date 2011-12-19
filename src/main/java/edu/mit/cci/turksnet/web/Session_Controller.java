package edu.mit.cci.turksnet.web;


import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String getForm(@PathVariable("id") Long id, @RequestParam("assignmentId") String assignmentId, @RequestParam("workerId") String workerId, Model model) {
        model.addAttribute("workerId", workerId);
        model.addAttribute("assignmentId", assignmentId);
        model.addAttribute("submission", false);

        return "session_s/node/feedback";
    }


    @RequestMapping(value = "/{id}/turk/feedback", method = RequestMethod.POST)
    public String postFeedback(@PathVariable("id") Long id, @RequestParam("assignmentId") String assignmentId, @RequestParam("workerId") String workerId, @RequestParam("feedback") String feedback, Model model) {
        StringBuilder builder = new StringBuilder();
        builder.append("Worker: ").append(workerId).append("\n");
        builder.append("Session: ").append(id).append("\n");
        builder.append("Assignment: ").append(assignmentId).append("\n");
        builder.append("Message:\n\n");
        builder.append(feedback);


        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        SimpleMailMessage tmpl = context.getBean("mailMessage", SimpleMailMessage.class);
        JavaMailSender sender = context.getBean("mailSender", JavaMailSenderImpl.class);
        SimpleMailMessage message = new SimpleMailMessage(tmpl);
        message.setSentDate(new Date());
        message.setText(builder.toString());
        sender.send(message);
        model.addAttribute("submission", true);

        return "session_s/node/feedback";
    }

    @RequestMapping(value = "/{id}/application", method = RequestMethod.GET)
    public String getApplication(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        //@TODO better handling of invalid attempt to retrieve application

        HttpSession s = request.getSession();
        Worker w = extractWorkerFromSession(s);
        if (w == null && request.getParameter("workerid") != null) {
            w = Worker.findWorker(Long.parseLong(request.getParameter("workerid")));
        }
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
        model.addAttribute("sessionid", session.getId());
        model.addAttribute("appData", app.replace("${flash_lib_dir}", "/turksnet/resources/flash/"));


        return "session_s/app";
    }

    @RequestMapping(value = "/{id}/nodedata", method = RequestMethod.GET)
    @ResponseBody
    public String getDataForNode(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        //TODO do a better job with error handling here
        HttpSession s = request.getSession();
        Worker w = extractWorkerFromSession(s);
        if (w == null && request.getParameter("workerid") != null) {
            w = Worker.findWorker(Long.parseLong(request.getParameter("workerid")));
        }
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
    public String getScoreForNode(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        //TODO do a better job with error handling here

        HttpSession s = request.getSession();
        Worker w = extractWorkerFromSession(s);
        if (w == null && request.getParameter("workerid") != null) {
            w = Worker.findWorker(Long.parseLong(request.getParameter("workerid")));
        }
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
            for (Map.Entry<String, Object> ent : session.getExperiment().getActualPlugin().getScoreInformation(n).entrySet()) {
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
    public String postResults(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, JSONException {
        HttpSession s = request.getSession();
        Worker w = extractWorkerFromSession(s);
        if (w == null && request.getParameter("workerid") != null) {
            w = Worker.findWorker(Long.parseLong(request.getParameter("workerid")));
        }
        if (w == null) {
            throw new IllegalArgumentException("Could not identify worker; please login");
        }
        Session_ session = Session_.findSession_(id);
        Node n = Node.findNodesByWorkerAndSession_(w, session).getSingleResult();
        if (n == null) {
            throw new IllegalArgumentException("Worker could not be resolved to a node");
        }

        String data = request.getParameter("data");
        log.debug("Received " + data + " from " + w.getUsername());
        session.getRunner().updateNode(n, data);
        return "{\"status\"=\"ok\"}";

    }


//    @RequestMapping(value = "/{id}/turk/app", method = RequestMethod.GET)
//    public String getTurkerApp(@PathVariable("id") Long id, @RequestParam("assignmentId") String assignmentId, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        //, , @RequestParam("turkerId") String turkerid\
//
//
//        /////@TODO Needs to be delegated out to runner?
//        String turkerid = "" + -1;
//        Node n = null;
//        String submitTo = null;
//        String hitId = request.getParameter("hitId");
//        Session_ s = Session_.findSession_(id);
//        if ("ASSIGNMENT_ID_NOT_AVAILABLE".equals(assignmentId)) {
//            n = Node.getDummyNode();
//            n.setSession_(s);
//
//        } else {
//
//            turkerid = request.getParameter("workerId");
//            submitTo = request.getParameter("turkSubmitTo");
//            n = s.getNodeForTurker(turkerid);
//            if (n == null) {
//                throw new IllegalArgumentException("Could not identify turker with id " + turkerid + " in session " + id);//forward to error page
//
//            }
//        }
//
//        model.addAttribute("node", n);
//        NodeForm nf = new NodeForm();
////        nf.setAssignmentId(assignmentId);
////        nf.setSubmitTo(submitTo);
////        nf.setHitId(hitId);
//        Plugin p = s.getExperiment().getActualPlugin();
//        Map<String,String> props = s==null?Collections.<String, String>emptyMap():s.getExperiment().getPropsAsMap();
//        Map<String,String> bonus = s==null? Collections.<String,String>emptyMap():p.getScoreInformation(n);
//
//        model.addAttribute("currentbonus",bonus.containsKey("CumulativeBonus")?bonus.get("CumulativeBonus"):"0.0");
//        model.addAttribute("round", s == null ? 0 : s.getIteration());
//        model.addAttribute("rounds", s == null ? 0 : s.getExperiment().getPropsAsMap().get(LoomTurkPlugin.PROP_ITERATION_COUNT));
//        model.addAttribute("nodeForm", nf);
//        model.addAttribute("turkerId", turkerid);
//        model.addAttribute("assignmentId", assignmentId);
//        model.addAttribute("submitTo", submitTo);
//        model.addAttribute("hitId", hitId);
//        model.addAttribute("sessionturnbonus",props.get(LoomTurkPlugin.PROP_SESSION_BONUS_VALUE));
//        model.addAttribute("sessionbonuscount",props.get(LoomTurkPlugin.PROP_SESSION_BONUS_COUNT));
//        model.addAttribute("sessionfinalbonus",props.get(LoomTurkPlugin.PROP_SESSION_BONUS_CORRECT));
//
//        model.addAttribute("privateData",U.jsonify(Node.mapifyStoryData(n.getPrivateData_())));
//        model.addAttribute("publicData",U.jsonify(Node.mapifyStoryData(n.getPublicData_())));
//
//        Map<String,Object> incoming = new HashMap<String, Object>();
//        for (Node i:n.getIncoming()) {
//            incoming.put(i.getId()+"",U.jsonify(Node.mapifyStoryData(i.getPublicData_())));
//
//        }
//        model.addAttribute("incomingData",U.jsonify(incoming));
//        String app = "Error retrieving application";
//        try {
//            app = p.getApplicationBody(n);
//        } catch (Exception e) {
//            log.error(e);
//        }
//        model.addAttribute("appData",app);
//
//        int nrounds = props.containsKey(LoomTurkPlugin.PROP_ITERATION_COUNT)?Integer.parseInt(props.get(LoomTurkPlugin.PROP_ITERATION_COUNT)):0;
//        float val = props.containsKey(LoomTurkPlugin.PROP_ASSIGNMENT_VALUE)?Float.parseFloat(props.get(LoomTurkPlugin.PROP_ASSIGNMENT_VALUE)):0f;
//
//        float max = val*nrounds;
//
//
//        if (props.size()>0) {
//            float turnval = Float.parseFloat(props.get(LoomTurkPlugin.PROP_SESSION_BONUS_VALUE));
//            int numturns = Integer.parseInt(props.get(LoomTurkPlugin.PROP_SESSION_BONUS_COUNT));
//            float finalbonus = Float.parseFloat(props.get(LoomTurkPlugin.PROP_SESSION_BONUS_CORRECT));
//            float maxvalue = numturns*turnval+finalbonus;
//            model.addAttribute("finalbonus",String.format("$%.2f",maxvalue));
//            max+=maxvalue;
//            model.addAttribute("maxpayout",String.format("$%.2f",max));
//        } else {
//            model.addAttribute("finalbonus","$0.00");
//             model.addAttribute("maxpayout",String.format("$%.2f",max));
//        }
//
//        return "session_s/node/app";
//    }


    @RequestMapping(value = "/{id}/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping(@PathVariable("id") Long id, Model model, HttpServletRequest request, HttpSession session) {
        Worker w = extractWorkerFromSession(session);
        Session_ s = Session_.findSession_(id);
        Map<String, Object> result = new HashMap<String, Object>();

        if (w == null) {
            result.put("status", "http_session_unavailable");

        } else {

            result = s.getRunner().ping(w,session);
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


    private static Worker extractWorkerFromSession(HttpSession s) {
        Worker w = s.getAttribute("workerid") == null ? null : Worker.findWorker((Long) s.getAttribute("workerid"));
        return w;
    }


}
