package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class AppelOffres implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
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
	@ManyToOne
	@JoinColumn(name="CODE_ACHETEUR_PUBLIC")
	private AcheteurPublic acheteurPublic;
	
	@OneToMany(mappedBy="appelOffre")
	private Collection<Marche> marches;
	
	public AppelOffres() {
		super();
	}

	public AppelOffres(String objetAO, String categorieAO, String secteurAO, String procedureAO, Date dateAO,
			String dossierConsultationAO, AcheteurPublic acheteurPublic) {
		super();
		this.objetAO = objetAO;
		this.categorieAO = categorieAO;
		this.secteurAO = secteurAO;
		this.procedureAO = procedureAO;
		this.dateAO = dateAO;
		this.dossierConsultationAO = dossierConsultationAO;
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

	public AcheteurPublic getAcheteurPublic() {
		return acheteurPublic;
	}

	public void setAcheteurPublic(AcheteurPublic acheteurPublic) {
		this.acheteurPublic = acheteurPublic;
	}

	public Collection<Marche> getMarches() {
		return marches;
	}

	public void setMarches(Collection<Marche> marches) {
		this.marches = marches;
	}

	
}
