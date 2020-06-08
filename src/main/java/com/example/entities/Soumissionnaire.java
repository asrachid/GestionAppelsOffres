package com.example.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	
	@OneToMany(mappedBy = "soumissionnaire", cascade=CascadeType.ALL)
	private Set<Soumission> soumissions = new HashSet<>();
	
	
	public Soumissionnaire() {
		super();
	}

	public Soumissionnaire(String password, String email, boolean active, String profileS) {
		super(password, email, active);
		this.profileS = profileS;
	}

	public String getProfileS() {
		return profileS;
	}

	public void setProfileS(String profileS) {
		this.profileS = profileS;
	}

	public Set<Soumission> getSoumissions() {
		return soumissions;
	}

	public void setSoumissions(Set<Soumission> soumissions) {
		this.soumissions = soumissions;
	}

	
	
	
}
