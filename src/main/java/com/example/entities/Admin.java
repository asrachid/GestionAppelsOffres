package com.example.entities;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Utilisateur{

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="admin")
	private Collection<Document> documents;
	
	public Admin() {
		super();
	}
	
	public Admin(String password, String username, String email, boolean active) {
		super(password, username, email, active);
	}

	public Collection<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}
	
}
