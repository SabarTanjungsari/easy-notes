package org.sabar.easynotes.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.sabar.easynotes.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	Page<Comment> findByNoteId(Long noteId, Pageable pageable);
    Optional<Comment> findByIdAndNoteId(Long id, Long noteId);
}
