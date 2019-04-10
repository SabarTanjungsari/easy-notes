package org.sabar.easynotes.controller;

import org.sabar.easynotes.model.Status;
import org.sabar.easynotes.model.User;
import org.sabar.easynotes.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/status")
public class StatusController {

    private static final String VIEW_INDEX = "status/index";
    private static final String VIEW_SHOW_ADD = "status/showAdd";

    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("status", statusRepository.findAll());
        return VIEW_INDEX;
    }
    
    @GetMapping("/showAdd")
    public String showAddForm(Status status) {
        return VIEW_SHOW_ADD;
    }
}
