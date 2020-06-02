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
	
	@OneToMany(mappedBy="admin")
	private Collection<Annonce> annonces;
	
	public Admin() {
		super();
	}
	
	public Admin(String password, String email, boolean active) {
		super(password, email, active);
	}

	public Collection<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}

	public Collection<Annonce> getAnnonces() {
		return annonces;
	}

	public void setAnnonces(Collection<Annonce> annonces) {
		this.annonces = annonces;
	}
	
}
