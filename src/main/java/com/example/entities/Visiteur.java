package com.example.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VISITEUR")
public class Visiteur extends Utilisateur{

	private static final long serialVersionUID = 1L;

	public Visiteur() {
		super();
	}

	public Visiteur(Long code, String login, String email) {
		super(code, login, email);
	}
	
	
}
