package org.sabar.easynotes.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "ad_user")
public class User extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ad_user_id")
	private Long id;

	@Column(unique = true)
	private String name;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.PERSIST })
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "ad_user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "ad_role_id") })
	@JsonManagedReference
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(Long id, String name, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
