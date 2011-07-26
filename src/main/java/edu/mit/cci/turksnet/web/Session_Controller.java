package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import org.apache.log4j.Logger;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.Session;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@RooWebScaffold(path = "session_s", formBackingObject = Session_.class)
@RequestMapping("/session_s")
@Controller
public class Session_Controller {

    private static Logger log = Logger.getLogger(Session_Controller.class);

     @RequestMapping(value = "/{id}/turk/{turkerid}", method = RequestMethod.GET, headers = "accept=text/xml")
     @ResponseBody
     public Node getNodeForTurker(@PathVariable("id") Long id, @PathVariable("turkerid") String turkerid) {
         Session_ s = Session_.findSession_(id);
         for (Node n:s.getAvailableNodes()) {
            if (n.getTurkerId()!=null && n.getTurkerId().equals(turkerid)) {
                return n;
            }
         }
         for (Node n:s.getAvailableNodes()) {
             if (n.getTurkerId() == null) {
                 n.setTurkerId(turkerid);
                 n.merge();
                 return n;
             }
         }
         log.warn("Could not identify node for turker "+turkerid);
         return null;
    }

    @RequestMapping(value = "/{id}/turk/app", method = RequestMethod.GET)
    public String getTurkerApp(@PathVariable("id") Long id, @RequestParam("turkerid") String turkerid, Model model) {
        Session_ s = Session_.findSession_(id);
        Node node = null;
        for (Node n:s.getAvailableNodes()) {
            if (n.getTurkerId()!=null && n.getTurkerId().equals(turkerid)) {
                node = n;
                break;
            }
         }
        if (node == null) {
            throw new IllegalArgumentException( "Could not identify turker with id "+turkerid+" in session "+id );//forward to error page

        }
        model.addAttribute("node",node);
        return "session_s/node/app";
    }


     @RequestMapping(value = "/{id}/turk/{turkerid}", method = RequestMethod.POST)
     @ResponseBody
     public Node postDataForTurker(@PathVariable("id") Long id, @PathVariable("turkerid") String turkerid, @RequestParam("data") String data) {
         Session_ s = Session_.findSession_(id);
         log.debug("Received a post of "+data);
         for (Node n:s.getAvailableNodes()) {
            if (n.getTurkerId()!=null && n.getTurkerId().equals(turkerid)) {
                return n;
            }
         }
         for (Node n:s.getAvailableNodes()) {
             if (n.getTurkerId() == null) {
                 n.setTurkerId(turkerid);
                 n.merge();
                 return n;
             }
         }

         return null;
    }






}
