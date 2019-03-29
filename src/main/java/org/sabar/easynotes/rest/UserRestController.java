package org.sabar.easynotes.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.sabar.easynotes.model.Role;
import org.sabar.easynotes.model.User;
import org.sabar.easynotes.repository.RoleRepository;
import org.sabar.easynotes.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Get all user
	 */
	@GetMapping()
	public Page<User> getAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	/**
	 * Create new user
	 */
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	/**
	 * Get a single user
	 */
	@GetMapping("/{userId}")
	public User getNoteById(@PathVariable(value = "userId") Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	/**
	 * Update a user
	 */
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody User userDetail) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDetail.getName());

		User updateUser = userRepository.save(user);
		return updateUser;
	}

	/**
	 * Delete a user
	 */
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}

	/**
	 * Add role to user
	 */
	@PutMapping("/{userId}/role/{roleId}")
	public Set<Role> addRole(@PathVariable Long userId, @PathVariable(value = "roleId") Long roleId) {
		// Find a role
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

		/** Find user and add role to user */
		return userRepository.findById(userId).map((user) -> {
			user.getRoles().add(role);
			return userRepository.save(user).getRoles();
		}).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

}
