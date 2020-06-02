package com.example.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("AP")
public class AcheteurPublic extends Utilisateur{

	private static final long serialVersionUID = 1L;
	
	@Column (name="type_ap")
	private String type;
	@Column (name="profile_ap")
	private String profileAP;
	@OneToMany(mappedBy="acheteurPublic", 
				orphanRemoval = true,
				cascade = CascadeType.ALL)
	private Collection<AppelOffres> AppelsOffres;
	
	
	public AcheteurPublic() {
		super();
	}
	
	public AcheteurPublic(String password, String email, boolean active,  String type, String profileAP) {
		super(password, email, active);
		this.type = type;
		this.profileAP = profileAP;
	}



	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProfileAP() {
		return profileAP;
	}
	public void setProfileAP(String profileAP) {
		this.profileAP = profileAP;
	}

	public Collection<AppelOffres> getAppelsOffres() {
		return AppelsOffres;
	}

	public void setAppelsOffres(Collection<AppelOffres> appelsOffres) {
		AppelsOffres = appelsOffres;
	}
	
	
	
}
