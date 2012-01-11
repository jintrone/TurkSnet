package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.util.TestException;
import edu.mit.cci.turksnet.util.U;
import org.apache.log4j.Logger;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
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


    @RequestMapping(value = "/{id}/join", method = RequestMethod.GET)
    public String wait(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, TestException {


        Experiment e = Experiment.findExperiment(id);
        model.addAttribute("experimentid", e.getId());
        if (!e.getRunning()) {
            return "experiments/notavailable";
        }
        return configureResponse(e.getId(),e.getActualPlugin(), Plugin.Destination.LOGIN, null, model);
    }


    private String configureResponse(Long experimentId, Plugin p, Plugin.Destination d, Worker w, Model model) {

        if (w != null) {
            model.addAttribute("workerId", w.getId());
            model.addAttribute("workerName", w.getUsername());
        }
        model.addAttribute("experimentId", experimentId);


        if (d == Plugin.Destination.WAITING) {

            return "experiments/waiting";
        }

        String body = "";

        try {

            if (d == Plugin.Destination.LOGIN) {
                body = p.getLoginApp();
            } else if (d == Plugin.Destination.QUALIFICATIONS) {
                body = p.getQualificationApp();
            } else if (d == Plugin.Destination.TRAINING) {
                body = p.getTrainingApp();
            } else {
                return "experiments/notavailable";
            }


        } catch (Exception e) {
            log.error(e);
            return "experiments/notavailable";
        }
        //@TODO fixme - this is a pretty bad hack.  need to figure out fixes
        model.addAttribute("appData", body.replace("${flash_lib_dir}", "/turksnet/resources/flash/"));
        return "experiments/flash";
    }


    @RequestMapping(value = "/{id}/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@PathVariable("id") Long id, @RequestParam("login") String login, @RequestParam("password") String password, Model model, HttpSession session) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
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
            w.setLastCheckin(0l);
            w.persist();
            loginSuccess(Experiment.findExperiment(id), w, result);
        }

        return U.jsonify(result);
    }


    @RequestMapping(value = "/{id}/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@PathVariable("id") Long id, @RequestParam("login") String login, @RequestParam("password") String password, Model model, HttpSession session) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<Worker> ws = Worker.findWorkersByUsernameAndPassword(login, password).getResultList();
        Map<String, Object> result = new HashMap<String, Object>();
        if (ws.isEmpty()) {
            result.put("status", "login_failure");
        } else if (ws.size() > 1) {
            log.error("WARNING:  Multiple users registered under the same name!");
            result.put("status", "server_error");
        } else {
            loginSuccess(Experiment.findExperiment(id), ws.get(0), result);
        }
        return U.jsonify(result);
    }

    public void loginSuccess(Experiment e, Worker w, Map<String, Object> result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Plugin p = e.getActualPlugin();
        result.put("status", "success");
        result.put("workerid", w.getId());
        result.put("forward", "experiments/" + e.getId() + "/next");


    }

    @RequestMapping(value = "/{id}/qualifications", method = RequestMethod.GET)
    public String getQualifications(@PathVariable("id") Long id, @RequestParam("workerId") Long workerId, Model model) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Worker worker = Worker.findWorker(workerId);
        if (worker == null) {
            return "redirect:/experiments/" + id + "/join";
        }
        return configureResponse(id, Experiment.findExperiment(id).getActualPlugin(), Plugin.Destination.QUALIFICATIONS, worker, model);
    }

    @RequestMapping(value = "/{id}/qualifications", method = RequestMethod.POST)
    @ResponseBody
    public String postQualifications(@PathVariable("id") Long id, @RequestParam("workerId") Long workerId, @RequestParam("data") String data) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Worker worker = Worker.findWorker(workerId);
        if (worker == null) {
            return "redirect:/experiments/" + id + "/join";
        }
        worker.setQualifications(data);
        worker.flush();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "success");
        Plugin p = Experiment.findExperiment(id).getActualPlugin();
        result.put("forward", p.getDestinationForEvent(worker, Plugin.Event.QUALIFICATIONS_SUBMITTED).url(id));
        return U.jsonify(result);
    }

    @RequestMapping(value = "/{id}/next", method = RequestMethod.GET)
    public String next(@PathVariable("id") Long id, @RequestParam("workerId") Long workerId, @RequestParam("event") String event, Model model) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Worker worker = Worker.findWorker(workerId);
        Experiment e = Experiment.findExperiment(id);
        Plugin p = e.getActualPlugin();
        Plugin.Destination d = p.getDestinationForEvent(worker, Plugin.Event.valueOf(event));
        return configureResponse(id, p, d, worker, model);
    }

    @RequestMapping(value = "/{id}/training", method = RequestMethod.GET)
    public String getTraining(@PathVariable("id") Long id, @RequestParam("workerId") Long workerId, Model model) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Worker worker = Worker.findWorker(workerId);
        if (worker == null) {
            return "redirect:/experiments/" + id + "/join";
        }
        return configureResponse(id, Experiment.findExperiment(id).getActualPlugin(), Plugin.Destination.TRAINING, worker, model);
    }

    @RequestMapping(value = "/{id}/trainingdata", method = RequestMethod.GET)
    @ResponseBody
    public String getTrainingData(@PathVariable("id") Long id, @RequestParam("workerId") Long workerId, HttpServletRequest request, Model model) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Worker worker = Worker.findWorker(workerId);
        Experiment e = Experiment.findExperiment(id);
        Plugin p = e.getActualPlugin();
        return U.safejson(p.getTrainingData(worker, e, request.getParameterMap()));

    }

    @RequestMapping(value = "/{id}/trainingdata", method = RequestMethod.POST)
    @ResponseBody
    public String setTrainingData(@PathVariable("id") Long id, @RequestParam("workerId") Long workerId, HttpServletRequest request, Model model) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Worker worker = Worker.findWorker(workerId);
        Experiment e = Experiment.findExperiment(id);
        Plugin p = e.getActualPlugin();
        p.addTrainingData(worker, e, request.getParameterMap());
        return "{\"status\":\"ok\"}";
    }


    @RequestMapping(value = "/{id}/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping(@PathVariable("id") Long id, Model model, @RequestParam("workerId") Long workerid, HttpServletRequest request) {
        Worker w = Worker.findWorker(workerid);
        Experiment e = Experiment.findExperiment(id);
        Map<String, Object> result = new HashMap<String, Object>();
        if (w == null) {
            result.put("status", "http_session_unavailable");

        } else if (w.getCurrentAssignment() != null) {
            result.put("status", "worker_assigned");
            result.put("forward_url", "session_s/" + w.getCurrentAssignment().getId() + "/application");

        } else if (!e.getRunning()) {
            log.warn("Experiment not running for  " + w.getUsername());
            result.put("status", "experiment_closed");

        } else {
            result.put("status", "waiting");
            result.putAll(e.getWaitingRoomManager().checkin(w));

        }
        String response = U.jsonify(result);
        log.debug("Sending: " + response);
        return response;


    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return "{\"status\":\"error\"}";
    }


}
