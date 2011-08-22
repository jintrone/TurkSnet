package edu.mit.cci.turksnet.web;


import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import org.apache.log4j.Logger;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RooWebScaffold(path = "session_s", formBackingObject = Session_.class)
@RequestMapping("/session_s")
@Controller
public class Session_Controller {

    private static Logger log = Logger.getLogger(Session_Controller.class);

    @RequestMapping(value = "/{id}/turk/{turkerid}", method = RequestMethod.GET, headers = "accept=text/xml")
    @ResponseBody
    public Node getNodeForTurker(@PathVariable("id") Long id, @PathVariable("turkerid") String turkerid) {
        Session_ s = Session_.findSession_(id);
        Node n = s.getNodeForTurker(turkerid);
        if (n != null) {
            return n;
        }
        throw new IllegalArgumentException("Could not identify turker with id " + turkerid + " in session " + id);//forward to error page


    }

    //for the html app

    @RequestMapping(value = "/{id}/turk/app", method = RequestMethod.GET)
    public String getTurkerApp(@PathVariable("id") Long id, @RequestParam("assignmentId") String assignmentId, Model model, HttpServletRequest request) {
        //, , @RequestParam("turkerId") String turkerid\
        String turkerid = "" + -1;
        Node n = null;
        String submitTo = null;
        String hitId = request.getParameter("hitId");
        if ("ASSIGNMENT_ID_NOT_AVAILABLE".equals(assignmentId)) {
            n = Node.getDummyNode();

        } else {
            Session_ s = Session_.findSession_(id);
            turkerid = request.getParameter("workerId");
            submitTo = request.getParameter("turkSubmitTo");
            n = s.getNodeForTurker(turkerid);
            if (n == null) {
                throw new IllegalArgumentException("Could not identify turker with id " + turkerid + " in session " + id);//forward to error page

            }
        }

        model.addAttribute("node", n);
        NodeForm nf = new NodeForm();
//        nf.setAssignmentId(assignmentId);
//        nf.setSubmitTo(submitTo);
//        nf.setHitId(hitId);
        model.addAttribute("nodeForm", nf);
        model.addAttribute("turkerId", turkerid);
        model.addAttribute("assignmentId",assignmentId);
        model.addAttribute("submitTo",submitTo);
        model.addAttribute("hitId",hitId);
        return "session_s/node/app";
    }


    @RequestMapping(value = "/{id}/turk/{turkerid}", method = RequestMethod.POST)
    public String postDataForTurker(@Valid NodeForm form, BindingResult result, @PathVariable("id") Long id, @PathVariable("turkerid") String turkerid, Model model, HttpServletRequest request) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Session_ s = Session_.findSession_(id);
        s.processNodeResults(turkerid, form);
        return "redirect:/session_s";
    }


}
