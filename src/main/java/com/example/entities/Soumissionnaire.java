package com.example.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("SM")
public class Soumissionnaire extends Utilisateur{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name="profile_sm")
	private String profileS;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			mappedBy = "soumissionnaires")
	private Set<AppelOffres> appelsOffres=new HashSet<>();
	
	public Soumissionnaire() {
		super();
	}

	public Soumissionnaire(String password, String username, String email, boolean active, String profileS) {
		super(password, username, email, active);
		this.profileS = profileS;
	}

	public String getProfileS() {
		return profileS;
	}

	public void setProfileS(String profileS) {
		this.profileS = profileS;
	}

	public Set<AppelOffres> getAppelsOffres() {
		return appelsOffres;
	}

	public void setAppelsOffres(Set<AppelOffres> appelsOffres) {
		this.appelsOffres = appelsOffres;
	}
	
	
	
}
