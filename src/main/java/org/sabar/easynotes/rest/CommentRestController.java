package org.sabar.easynotes.rest;

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
import org.sabar.easynotes.model.Comment;
import org.sabar.easynotes.repository.CommentRepository;
import org.sabar.easynotes.repository.NoteRepository;

@RestController
@RequestMapping("/api/notes")
public class CommentRestController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private NoteRepository noteRepository;

	/**
	 * Get all comments by noteId
	 */
	@GetMapping("/{noteId}/comments")
	public Page<Comment> getAllCommentsByNoteId(@PathVariable(value = "noteId") Long noteId, Pageable pageable) {
		return commentRepository.findByNoteId(noteId, pageable);
	}

	/**
	 * Create a new comment by noteId
	 */
	@PostMapping("/{noteId}/comments")
	public Comment createComment(@PathVariable(value = "noteId") Long noteId, @Valid @RequestBody Comment comment) {
		return noteRepository.findById(noteId).map(note -> {
			comment.setNote(note);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("NoteId", "id", noteId));
	}

	/**
	 * 
	 * Edit comment By noteId & commentId
	 */
	@PutMapping("/{noteId}/comments/{commentId}")
	public Comment updateComment(@PathVariable(value = "noteId") Long noteId,
			@PathVariable(value = "commentId") Long commentId, @Valid @RequestBody Comment commentRequest) {
		if (!noteRepository.existsById(noteId)) {
			throw new ResourceNotFoundException("Note", "id", noteId);
		}

		return commentRepository.findById(commentId).map(comment -> {
			comment.setText(commentRequest.getText());
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
	}

	/**
	 * Get comment by commentId
	 */
	@GetMapping("/comments/{commentId}")
	public Comment getNoteById(@PathVariable(value = "commentId") Long commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", commentId));
	}

	/**
	 * Delete comment
	 */
	@DeleteMapping("/notes/{noteId}/comments/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "noteId") Long noteId,
			@PathVariable(value = "commentId") Long commentId) {
		return commentRepository.findByIdAndNoteId(noteId, commentId).map(comment -> {
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
	}
}
