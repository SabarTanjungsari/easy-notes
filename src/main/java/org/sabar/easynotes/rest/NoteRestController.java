package org.sabar.easynotes.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.sabar.easynotes.repository.NoteRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/note")
public class NoteRestController {

	@Autowired
	NoteRepository noteRepository;

	/**
	 * Get All Notes
	 */
	@GetMapping()
	public List<Note> getAllNotes(Model model) {
		return noteRepository.findAll();
	}

	/**
	 * Create a new Note
	 */
	@PostMapping()
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note);
	}

	/**
	 * Get a Single Note
	 */
	@GetMapping("/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}

	/**
	 * Update a Note
	 */
	@PutMapping("/{noteId}")
	public Note updateNote(@PathVariable(value = "noteId") Long noteId, @Valid @RequestBody Note noteDetails) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
		note.setTitle(noteDetails.getTitle());

		Note updateNote = noteRepository.save(note);
		return updateNote;
	}

	/**
	 * Delete a Note
	 */
	@DeleteMapping("/{noteId}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "noteId") Long noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}

}
