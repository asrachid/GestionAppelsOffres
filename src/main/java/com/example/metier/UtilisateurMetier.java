package com.example.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.APRepository;
import com.example.dao.AdminRepository;
import com.example.dao.SoumissionnaireRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.AcheteurPublic;
import com.example.entities.Admin;
import com.example.entities.Soumissionnaire;
import com.example.entities.Utilisateur;

@Service
@Transactional
public class UtilisateurMetier {
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private APRepository apRepo;
	@Autowired
	private SoumissionnaireRepository smRepo;
	
	/*
	public void Soumissionner(Long codeAO, Long codeUser) {
		if(findByIdAO(codeAO).isPresent() && findByIdUser(codeUser).isPresent()){
	  		Optional<Utilisateur> sm = findByIdUser(codeUser); 	
	 		Optional<AppelOffres> ao = findByIdAO(codeAO); 
	 		AppelOffres ao=appelOffresRepository.getOne(codeAO);
	 		Soumissionnaire sm=(Soumissionnaire) soumissionnaireRepository.getOne(codeUser); 
	 		ao.getSoumissionnaires().add(sm);
	 		sm.getAppelsOffres().add(ao); 
	 		appelOffresRepository.save(ao);
		}
	}
	*/
	
	public void changePassword(String password,long id) {
		userRepo.changePassword(password, id);
	}
	
	public void changeEmail(String email,long id) {
		userRepo.changeEmail(email, id);
	}
	
	public void changeActive(Boolean active,long id) {
		userRepo.changeActive(active, id);
	}
	
	public void saveAdmin(Admin user) { 
	 	userRepo.save(user);
	}
	
	public void saveAP(AcheteurPublic user) { 
	 	userRepo.save(user);
	}
	
	public void saveSM(Soumissionnaire user) { 
	 	userRepo.save(user);
	}
	
	public Utilisateur getUser(Long id) {
		Utilisateur user = userRepo.findById(id).get();
		if(user==null) throw new RuntimeException("utilisateur Introuvable!");
		return user;
	}
	
	public void deleteUser(Long id) {
 		userRepo.deleteById(id);
	}
	
	public List<Admin> getAdmins() { 
	 	return adminRepo.findAll(); 
	}
	
	public Admin getAdminByEmail(@Param("email") String email) {
		return adminRepo.getAdminByEmail(email);
	}
	
	public AcheteurPublic getAPByEmail(String email) {
		return apRepo.getAPByEmail(email);
	}
	
	public List<AcheteurPublic> getAPs() { 
	 	return apRepo.findAll(); 
	}
	
	public List<Soumissionnaire> getSMs() { 
	 	return smRepo.findAll(); 
	}
	
 	public void changeProfilAP(String profileAP, long id) {
 		apRepo.changeProfilAP(profileAP, id);
 	}
	
 	public void changeTypeAP(String type, long id) {
 		apRepo.changeTypeAP(type, id);
 	}
	
	public Admin getAdmin(long id) {
		return adminRepo.findById(id).get();
	}
	
	public AcheteurPublic getAP(long id) {
		return apRepo.findById(id).get();
	}
	
	public Soumissionnaire getSM(long id) {
		return smRepo.findById(id).get();
	}
	
	public String generatedPassword() {
 		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    String pass = "";
	    int x;
	    for(x=0;x<=10;x++) {
	       int i = (int)Math.floor(Math.random() * 62); 
	       pass += chars.charAt(i);
	    }
	    return pass;
 	}
}
