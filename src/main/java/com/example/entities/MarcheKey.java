package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MarcheKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column (name = "code_ao")
	private Long codeAppelOffres;
	@Column (name = "code_sm")
	private Long codeSoumissionnaire;
	
	public MarcheKey() {
		super();
	}

	public MarcheKey(Long codeAppelOffres, Long codeSoumissionnaire) {
		super();
		this.codeAppelOffres = codeAppelOffres;
		this.codeSoumissionnaire = codeSoumissionnaire;
	}

	public Long getCodeAppelOffres() {
		return codeAppelOffres;
	}

	public void setCodeAppelOffres(Long codeAppelOffres) {
		this.codeAppelOffres = codeAppelOffres;
	}

	public Long getCodeSoumissionnaire() {
		return codeSoumissionnaire;
	}

	public void setCodeSoumissionnaire(Long codeSoumissionnaire) {
		this.codeSoumissionnaire = codeSoumissionnaire;
	}
	
	
}
