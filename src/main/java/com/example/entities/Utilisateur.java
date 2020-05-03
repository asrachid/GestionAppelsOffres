package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
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
	@Column (name="code_user")
	private Long codeUser;
	@Column (name="login_user")
	private String loginUser;
	@Column (name="email_user")
	private String emailUser;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Long codeUser, String loginUser, String emailUser) {
		super();
		this.codeUser = codeUser;
		this.loginUser = loginUser;
		this.emailUser = emailUser;
	}

	public Long getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(Long codeUser) {
		this.codeUser = codeUser;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	
	
	
}
