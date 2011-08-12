package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.domain.UserRole;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "userroles", formBackingObject = UserRole.class)
@RequestMapping("/userroles")
@Controller
public class UserRoleController {
}
