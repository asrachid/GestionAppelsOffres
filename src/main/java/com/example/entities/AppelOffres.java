package com.example.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class AppelOffres implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="code_ao")
	private Long codeAO;
	
	@Column (name="objet_ao")
	private String objetAO;
	
	@Column (name="categorie_ao")
	private String categorieAO;
	
	@Column (name="structure_ao")
	private String secteurAO;
	
	@Column (name="procedure_ao")
	private String procedureAO;
	
	@Column (name="date_ao")
	private Date dateAO;
	
	@Column (name="dossier_consultation_ao")
	private String dossierConsultationAO;
	
	private Date dateLimiteRemisePlis;
	
	private Date dateExecution;
	
	private String lieuExecution;
	
	@ManyToOne
	@JoinColumn(name="code_ap")
	private AcheteurPublic acheteurPublic;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="soumissionner",
			joinColumns= {@JoinColumn(name="code_AO")},
			inverseJoinColumns= {@JoinColumn(name="code_sm")})
    private Set<Soumissionnaire> soumissionnaires=new HashSet<>();
	
	
	
	public AppelOffres() {
		super();
	}

	public AppelOffres(String objetAO, String categorieAO, String secteurAO, String procedureAO, Date dateAO,
			String dossierConsultationAO, Date dateLimiteRemisePlis, Date dateExecution, String lieuExecution,
			AcheteurPublic acheteurPublic) {
		super();
		this.objetAO = objetAO;
		this.categorieAO = categorieAO;
		this.secteurAO = secteurAO;
		this.procedureAO = procedureAO;
		this.dateAO = dateAO;
		this.dossierConsultationAO = dossierConsultationAO;
		this.dateLimiteRemisePlis = dateLimiteRemisePlis;
		this.dateExecution = dateExecution;
		this.lieuExecution = lieuExecution;
		this.acheteurPublic = acheteurPublic;
	}

	public Long getCodeAO() {
		return codeAO;
	}

	public void setCodeAO(Long codeAO) {
		this.codeAO = codeAO;
	}

	public String getObjetAO() {
		return objetAO;
	}

	public void setObjetAO(String objetAO) {
		this.objetAO = objetAO;
	}

	public String getCategorieAO() {
		return categorieAO;
	}

	public void setCategorieAO(String categorieAO) {
		this.categorieAO = categorieAO;
	}

	public String getSecteurAO() {
		return secteurAO;
	}

	public void setSecteurAO(String secteurAO) {
		this.secteurAO = secteurAO;
	}

	public String getProcedureAO() {
		return procedureAO;
	}

	public void setProcedureAO(String procedureAO) {
		this.procedureAO = procedureAO;
	}

	public Date getDateAO() {
		return dateAO;
	}

	public void setDateAO(Date dateAO) {
		this.dateAO = dateAO;
	}

	public String getDossierConsultationAO() {
		return dossierConsultationAO;
	}

	public void setDossierConsultationAO(String dossierConsultationAO) {
		this.dossierConsultationAO = dossierConsultationAO;
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

	public AcheteurPublic getAcheteurPublic() {
		return acheteurPublic;
	}

	public void setAcheteurPublic(AcheteurPublic acheteurPublic) {
		this.acheteurPublic = acheteurPublic;
	}

	public Set<Soumissionnaire> getSoumissionnaires() {
		return soumissionnaires;
	}

	public void setSoumissionnaires(Set<Soumissionnaire> soumissionnaires) {
		this.soumissionnaires = soumissionnaires;
	}
	
	
	
}
