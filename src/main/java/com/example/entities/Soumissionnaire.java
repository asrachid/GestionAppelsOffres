package com.example.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("SM")
public class Soumissionnaire extends Utilisateur{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name="profile_sm")
	private String profileS;
	
	@OneToMany(mappedBy="soumissionnaire")
	private Collection<Marche> marches;
	
	public Soumissionnaire() {
		super();
	}
	
	public Soumissionnaire(Long code, String login, String email, String profileS) {
		super(code, login, email);
		this.profileS = profileS;
	}

	public String getProfileS() {
		return profileS;
	}

	public void setProfileS(String profileS) {
		this.profileS = profileS;
	}

	public Collection<Marche> getMarches() {
		return marches;
	}



	public void setMarches(Collection<Marche> marches) {
		this.marches = marches;
	}
	
	
}
