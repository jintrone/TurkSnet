package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.SessionLog;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "sessionlogs", formBackingObject = SessionLog.class)
@RequestMapping("/sessionlogs")
@Controller
public class SessionLogController {
}
