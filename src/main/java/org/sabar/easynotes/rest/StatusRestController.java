package org.sabar.easynotes.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.sabar.easynotes.exception.ResourceNotFoundException;
import org.sabar.easynotes.model.Note;
import org.sabar.easynotes.model.Status;
import org.sabar.easynotes.repository.NoteRepository;
import org.sabar.easynotes.repository.StatusRepository;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/status")
public class StatusRestController {

    @Autowired
    StatusRepository statusRepository;

    /**
     * Get All Notes
     */
    @GetMapping()
    public List<Status> getAllStatus(Model model) {
        return statusRepository.findAll();
    }

}
