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

import javax.management.Query;
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
    public String getTurkerApp(@PathVariable("id") Long id, @RequestParam("assignmentId") String assignmentId, @RequestParam("turkerId") String turkerid, Model model, HttpServletRequest request) {
        System.err.println("Query: "+ request.getQueryString());
        Session_ s = Session_.findSession_(id);

        Node n = s.getNodeForTurker(turkerid);
        if (n == null) {
            throw new IllegalArgumentException("Could not identify turker with id " + turkerid + " in session " + id);//forward to error page

        }
        model.addAttribute("node", n);
        NodeForm nf = new NodeForm();
        nf.setAssignmentId(assignmentId);
        model.addAttribute("nodeForm", nf);
        model.addAttribute("turkerId", turkerid);
        return "session_s/node/app";
    }


    @RequestMapping(value = "/{id}/turk/{turkerid}", method = RequestMethod.POST)
    public String postDataForTurker(@Valid NodeForm form, BindingResult result, @PathVariable("id") Long id, @PathVariable("turkerid") String turkerid, Model model, HttpServletRequest request) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.err.println("Query: "+ request.getQueryString());

        for (Object key : request.getParameterMap().keySet()) {
            String skey = key.toString();
            System.err.println("Key:" + skey + " , " + "Value:" + request.getParameterValues(skey)[0]);
        }
        Session_ s = Session_.findSession_(id);
        s.processNodeResults(turkerid, form);
        return "redirect:/session_s";
    }


}
