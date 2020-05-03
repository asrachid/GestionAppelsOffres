package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Marche implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MarcheKey code;
	
	@ManyToOne
	@MapsId("CODE_APPEL_OFFRES")
	@JoinColumn(name="code_ao")
	private AppelOffres appelOffre;
	
	@ManyToOne
	@MapsId("codeSoumissionnaire")
	@JoinColumn(name="code_sm")
	private Soumissionnaire soumissionnaire;
	
	private Date dateLimiteRemisePlis;
	private Date dateExecution;
	private String lieuExecution;
	
	public Marche() {
		super();
	}

	public Marche(MarcheKey code, AppelOffres appelOffre, Soumissionnaire soumissionnaire, Date dateLimiteRemisePlis,
			Date dateExecution, String lieuExecution) {
		super();
		this.code=code;
		this.appelOffre = appelOffre;
		this.soumissionnaire = soumissionnaire;
		this.dateLimiteRemisePlis = dateLimiteRemisePlis;
		this.dateExecution = dateExecution;
		this.lieuExecution = lieuExecution;
	}

	public MarcheKey getCode() {
		return code;
	}

	public void setCode(MarcheKey code) {
		this.code = code;
	}

	public AppelOffres getAppelOffre() {
		return appelOffre;
	}

	public void setAppelOffre(AppelOffres appelOffre) {
		this.appelOffre = appelOffre;
	}

	public Soumissionnaire getSoumissionnaire() {
		return soumissionnaire;
	}

	public void setSoumissionnaire(Soumissionnaire soumissionnaire) {
		this.soumissionnaire = soumissionnaire;
	}

	public Date getDateLimiteRemisePlis() {
		return dateLimiteRemisePlis;
	}

	public void setDateLimiteRemisePlis(Date dateLimiteRemisePlis) {
		this.dateLimiteRemisePlis = dateLimiteRemisePlis;
	}

	public Date getDateExecution() {
		return dateExecution;
	}

	public void setDateExecution(Date dateExecution) {
		this.dateExecution = dateExecution;
	}

	public String getLieuExecution() {
		return lieuExecution;
	}

	public void setLieuExecution(String lieuExecution) {
		this.lieuExecution = lieuExecution;
	}
	
	
	
}
