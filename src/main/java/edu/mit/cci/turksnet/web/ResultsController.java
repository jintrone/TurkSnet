package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Results;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "resultses", formBackingObject = Results.class)
@RequestMapping("/resultses")
@Controller
public class ResultsController {
}
