package com.example.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ROLE",
discriminatorType=DiscriminatorType.STRING,length=5)
public class Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	private String password;
	private String username;
	private String email;
	private boolean active;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(String password, String username, String email, boolean active) {
		super();
		this.password = password;
		this.username = username;
		this.email = email;
		this.active=active;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
}
