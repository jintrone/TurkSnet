// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect NodeController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String NodeController.create(@Valid Node node, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("node", node);
            return "nodes/create";
        }
        node.persist();
        return "redirect:/nodes/" + encodeUrlPathSegment(node.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String NodeController.createForm(Model model) {
        model.addAttribute("node", new Node());
        return "nodes/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String NodeController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("node", Node.findNode(id));
        model.addAttribute("itemId", id);
        return "nodes/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String NodeController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("nodes", Node.findNodeEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Node.countNodes() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("nodes", Node.findAllNodes());
        }
        return "nodes/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String NodeController.update(@Valid Node node, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("node", node);
            return "nodes/update";
        }
        node.merge();
        return "redirect:/nodes/" + encodeUrlPathSegment(node.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String NodeController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("node", Node.findNode(id));
        return "nodes/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String NodeController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Node.findNode(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/nodes?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("nodes")
    public Collection<Node> NodeController.populateNodes() {
        return Node.findAllNodes();
    }
    
    @ModelAttribute("session_s")
    public Collection<Session_> NodeController.populateSession_s() {
        return Session_.findAllSession_s();
    }
    
    String NodeController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
