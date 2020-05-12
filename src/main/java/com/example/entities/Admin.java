package com.example.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Utilisateur{

	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	public Admin(String password, String username, String email, boolean active) {
		super(password, username, email, active);
	}
	
	
}
