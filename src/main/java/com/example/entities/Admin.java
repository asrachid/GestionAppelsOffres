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

	public Admin(Long code, String login, String email) {
		super(code, login, email);
	}
	
	
}
