package org.sabar.easynotes.controller;

import org.sabar.easynotes.model.Status;
import org.sabar.easynotes.model.User;
import org.sabar.easynotes.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/status")
public class StatusController {

    private static final String VIEW_INDEX = "status/index";

    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("status", statusRepository.findAll());
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Status save(@RequestBody Status status) {
        statusRepository.save(status);
        return status;
    }

}
