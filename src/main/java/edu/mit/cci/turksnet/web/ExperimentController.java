package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.plugins.SessionCreationException;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

@RooWebScaffold(path = "experiments", formBackingObject = Experiment.class)
@RequestMapping("/experiments")
@Controller
public class ExperimentController {


    private static Logger log = Logger.getLogger(ExperimentController.class);

    private static class NewlineReplacementEditor extends PropertyEditorSupport {
        @Override
        public String getAsText() {
           Object value = getValue();
           return value != null?((String)value).replace(";","\n"):"";
        }

        public void setAsText(String txt) {
            setValue(txt.replace("\n",";"));
        }
    }

     @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Experiment experiment, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("experiment", experiment);
            return "experiments/create";
        }
         try {
         experiment.getActualPlugin().preprocessProperties(experiment);
         } catch (Exception e) {
             log.error("Error processing properties file");
         }
         experiment.persist();

        return "redirect:/experiments/" + encodeUrlPathSegment(experiment.getId().toString(), request);
    }

     @RequestMapping(value = "/{id}/run", method = RequestMethod.POST)
    public String run(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
         Experiment e = Experiment.findExperiment(id);
         Session_ session = null;
         try {
             session = e.createSession();
             session.run();
         } catch (Exception e1) {
             e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             System.err.println("Could not run session!");
             return "redirect:/experiments/" + encodeUrlPathSegment(id.toString(), request);

         }

        return "redirect:/session_s/" + encodeUrlPathSegment(session.getId().toString(), request);
    }




}
