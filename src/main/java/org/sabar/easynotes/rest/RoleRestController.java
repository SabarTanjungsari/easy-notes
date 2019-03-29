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
import org.sabar.easynotes.model.Role;
import org.sabar.easynotes.repository.RoleRepository;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Get all role
	 */
	@GetMapping()
	public Page<Role> getAllRole(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	/**
	 * Create new role
	 */
	@PostMapping
	public Role createRole(@Valid @RequestBody Role role) {
		return roleRepository.save(role);
	}

	/**
	 * Get a single user
	 
	@GetMapping("/{userId}")
	public User getNoteById(@PathVariable(value = "userId") Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	/**
	 * Update a user
	 
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
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
	*/
}
