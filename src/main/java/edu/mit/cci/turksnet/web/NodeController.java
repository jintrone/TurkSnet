package edu.mit.cci.turksnet.web;

import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.plugins.LoomTurkPlugin;
import edu.mit.cci.turksnet.plugins.Plugin;
import edu.mit.cci.turksnet.util.SynchroRunner;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RooWebScaffold(path = "nodes", formBackingObject = Node.class)
@RequestMapping("/nodes")
@Controller
public class NodeController {






}
