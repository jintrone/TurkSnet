package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.util.U;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RooWebScaffold(path = "experiments", formBackingObject = Experiment.class)
@RequestMapping("/experiments")
@Controller
public class ExperimentController {


    private static Logger log = Logger.getLogger(ExperimentController.class);

    private static class NewlineReplacementEditor extends PropertyEditorSupport {
        @Override
        public String getAsText() {
            Object value = getValue();
            return value != null ? ((String) value).replace(";", "\n") : "";
        }

        public void setAsText(String txt) {
            setValue(txt.replace("\n", ";"));
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
    public String run(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Experiment e = Experiment.findExperiment(id);
        e.run();
        model.addAttribute("experiment", Experiment.findExperiment(id));
        model.addAttribute("itemId", id);
        return "experiments/show";

    }

    @RequestMapping(value = "/{id}/join", method = RequestMethod.GET)
    public String wait(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Experiment e = Experiment.findExperiment(id);
        model.addAttribute("experimentid", e.getId());
        if (!e.getRunning()) {
            return "experiments/notavailable";
        }
        return "experiments/waiting";

    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public String goToLatest(Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Experiment> e = new ArrayList<Experiment>(Experiment.findAllExperiments());
        Collections.sort(e, new Comparator<Experiment>() {
            @Override
            public int compare(Experiment experiment, Experiment experiment1) {
                return -1 * experiment.getId().compareTo(experiment1.getId());
            }
        });
        for (Experiment ex : e) {
            if (ex.getRunning()) {
                return "redirect:/experiments/" + ex.getId() + "/join";
            }
        }
        return "experiments/notavailable";


    }


    @RequestMapping(value = "/{id}/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("login") String login, @RequestParam("password") String password, Model model, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Worker> wlist = Worker.findWorkersByUsername(login).getResultList();
        Worker w = wlist.size() == 0 ? null : wlist.get(0);
        if (w != null) {
            result.put("status", "username_exists");
            String nlogin = login;
            int i = 0;
            do {
                nlogin = login + i;
            }
            while (Worker.findWorkersByUsername(nlogin).getResultList().size() > 0);
            result.put("suggestion", nlogin);
        } else {
            w = new Worker();
            w.setUsername(login);
            w.setPassword(password);
            w.setLastCheckin(System.currentTimeMillis());
            w.persist();

            result.put("status", "success");
            result.put("workerid", w.getId());
            session.setAttribute("workerid", w.getId());
        }
        return U.jsonify(result);


    }

    @RequestMapping(value = "/{id}/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("login") String login, @RequestParam("password") String password, Model model, HttpSession session) {
        List<Worker> ws = Worker.findWorkersByUsernameAndPassword(login, password).getResultList();
        Map<String, Object> result = new HashMap<String, Object>();
        if (ws.isEmpty()) {
            result.put("status", "login_failure");
        } else if (ws.size() > 1) {
            log.error("WARNING:  Multiple users registered under the same name!");
            result.put("status", "server_error");
        } else {
            session.setAttribute("workerid", ws.get(0).getId());
            result.put("status", "success");
            result.put("workerid", ws.get(0).getId());
        }
        return U.jsonify(result);


    }


    @RequestMapping(value = "/{id}/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping(@PathVariable("id") Long id, Model model, HttpServletRequest request, HttpSession session) {
        Worker w = Worker.findWorker((Long) session.getAttribute("workerid"));
        Experiment e = Experiment.findExperiment(id);
        Map<String, Object> result = new HashMap<String, Object>();
        if (w == null) {
            result.put("status", "http_session_unavailable");

        } else if (w.getCurrentAssignment() != null) {
            log.warn("Worker " + w.getUsername() + " checking in but not waiting; ignoring");
            result.put("status", "worker_assigned");
            result.put("session_url", "session_s/" + w.getCurrentAssignment().getId() + "/application");


        } else if (!e.getRunning()) {
            log.warn("Experiment not running for  " + w.getUsername());
            result.put("status", "experiment_closed");
        } else {
            result.put("status", "waiting");
            result.putAll(e.getWaitingRoomManager().checkin(w));

        }

        return U.jsonify(result);


    }


}
