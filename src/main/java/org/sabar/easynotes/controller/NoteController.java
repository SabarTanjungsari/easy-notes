package org.sabar.easynotes.controller;

import org.sabar.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {

    private static final String VIEW_INDEX = "note/index";

    @Autowired
    NoteRepository repo;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("notes", repo.findAll());
        return VIEW_INDEX;
    }
}
