package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RooWebScaffold(path = "nodes", formBackingObject = Node.class)
@RequestMapping("/nodes")
@Controller
public class NodeController {

}
