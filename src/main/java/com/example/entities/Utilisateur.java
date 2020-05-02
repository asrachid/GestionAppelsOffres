package com.example.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ROLE",
discriminatorType=DiscriminatorType.STRING,length=2)
public class Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long code;
	private String login;
	private String email;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Long code, String login, String email) {
		super();
		this.code=code;
		this.login = login;
		this.email = email;
	}

	

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
