package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
@Entity
public class Soumission {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="code_soumission")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "code_appel_offres")
	private AppelOffres appelOffres;
	
	@ManyToOne
	@JoinColumn(name = "code_soumissionnaire")
	private Soumissionnaire soumissionnaire;
	
	
	private String dossierCandidature;
	private String typeDoc;
	@Lob
	private byte[] data;
	
	public Soumission() {
		super();
	}
	public Soumission(AppelOffres appelOffres, Soumissionnaire soumissionnaire, String dossierCandidature,
			String typeDoc, byte[] data) {
		super();
		this.appelOffres = appelOffres;
		this.soumissionnaire = soumissionnaire;
		this.dossierCandidature = dossierCandidature;
		this.typeDoc = typeDoc;
		this.data = data;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AppelOffres getAppelOffres() {
		return appelOffres;
	}
	public void setAppelOffres(AppelOffres appelOffres) {
		this.appelOffres = appelOffres;
	}
	public Soumissionnaire getSoumissionnaire() {
		return soumissionnaire;
	}
	public void setSoumissionnaire(Soumissionnaire soumissionnaire) {
		this.soumissionnaire = soumissionnaire;
	}
	public String getDossierCandidature() {
		return dossierCandidature;
	}
	public void setDossierCandidature(String dossierCandidature) {
		this.dossierCandidature = dossierCandidature;
	}
	public String getTypeDoc() {
		return typeDoc;
	}
	public void setTypeDoc(String typeDoc) {
		this.typeDoc = typeDoc;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	
}
