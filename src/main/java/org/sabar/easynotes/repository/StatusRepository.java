package org.sabar.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.sabar.easynotes.model.Status;

import org.sabar.easynotes.model.User;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
