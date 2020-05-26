package com.example.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.APRepository;
import com.example.dao.AdminRepository;
import com.example.dao.AppelOffresRepository;
import com.example.dao.DocumentRepository;
import com.example.dao.SoumissionnaireRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.AcheteurPublic;
import com.example.entities.Admin;
import com.example.entities.AppelOffres;
import com.example.entities.Document;
import com.example.entities.Soumissionnaire;
import com.example.entities.Utilisateur;

@Service
@Transactional
public class MarchesPublicsMetier {

	@Autowired
	private AppelOffresRepository aoRepo;
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private APRepository apRepo;
	@Autowired
	private SoumissionnaireRepository smRepo;
	@Autowired
	private DocumentRepository docRepo;
	/*
	 * @Override public void Soumissionner(Long codeAO, Long codeUser) {
	 * if(findByIdAO(codeAO).isPresent() && findByIdUser(codeUser).isPresent()){
	 * Optional<Utilisateur> sm = findByIdUser(codeUser); Optional<AppelOffres> ao =
	 * findByIdAO(codeAO); AppelOffres ao=appelOffresRepository.getOne(codeAO);
	 * Soumissionnaire sm=(Soumissionnaire)
	 * soumissionnaireRepository.getOne(codeUser); ao.getSoumissionnaires().add(sm);
	 * sm.getAppelsOffres().add(ao); appelOffresRepository.save(ao);
	 * }
	 * }
	 */
	
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
	
	public void changePassword(String password,long id) {
		userRepo.changePassword(password, id);
	}
	
	public void changeEmail(String email,long id) {
		userRepo.changeEmail(email, id);
	}
	
	public void changeActive(Boolean active,long id) {
		userRepo.changeActive(active, id);
	}
	
 	public void changeProfilAP(String profileAP, long id) {
 		apRepo.changeProfilAP(profileAP, id);
 	}
	
 	public void changeTypeAP(String type, long id) {
 		apRepo.changeTypeAP(type, id);
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
	
	public Admin getAdmin(long id) {
		return adminRepo.findById(id).get();
	}
	
	public AcheteurPublic getAP(long id) {
		return apRepo.findById(id).get();
	}
	
	public Soumissionnaire getSM(long id) {
		return smRepo.findById(id).get();
	}
	
	public Utilisateur getUser(Long id) {
		Utilisateur user = userRepo.findById(id).get();
		if(user==null) throw new RuntimeException("utilisateur Introuvable!");
		return user;
	}
	
	public void deleteUser(Long id) {
 		userRepo.deleteById(id);
	}
	
	public List<AppelOffres> listAll(){
		return aoRepo.findAll();
	}
	
	public List<AppelOffres> listAOBySecteur(@Param("secteurAO") String secteurAO){
		return aoRepo.listAOBySecteur(secteurAO);
	}
	
	public List<AppelOffres> listAOByAPSec(AcheteurPublic acheteurPublic, String secteurAO){
		return aoRepo.listAOByAPSec(acheteurPublic, secteurAO);
	}
	
 	public ArrayList<String> listSecteurs(){
 		return aoRepo.listSecteurs();
 	}
 	
 	public Document saveFile(MultipartFile file) {
 		String nomDoc = file.getOriginalFilename();
 		try {
 			Document doc = new Document(nomDoc,file.getContentType(),file.getBytes());
 			return docRepo.save(doc);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		return null;
 	}
 	
 	public Optional<Document> getFile(Long fileId) {
 		return docRepo.findById(fileId);
 	}
 	
 	public List<Document> getFiles(){
 		return docRepo.findAll();
 	}
 	
 	public void deleteDoc(Long id) {
 		docRepo.deleteById(id);
	}
 	
 	public AppelOffres saveAO(String objetAO, String categorieAO, String secteurAO, Date dateLimiteRemisePlis, Date dateExecution, String lieuExecution,
			AcheteurPublic acheteurPublic,MultipartFile file) {
 		String nomDoc = file.getOriginalFilename();
 		try {
 			AppelOffres ao = new AppelOffres(objetAO, categorieAO, secteurAO, new Date(),
 					nomDoc, dateLimiteRemisePlis, dateExecution, lieuExecution, acheteurPublic, file.getContentType(),file.getBytes());
 			return aoRepo.save(ao);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		return null;
 	}
 	
 	public void changeDateLimiteRemisePlis(Date dateLimiteRemisePlis, long codeAO) {
 		aoRepo.changeDateLimiteRemisePlis(dateLimiteRemisePlis, codeAO);
 	}
 	
 	public void changeLieuExecution(String lieuExecution, long codeAO) {
 		aoRepo.changeLieuExecution(lieuExecution, codeAO);
 	}
 	
 	public void changeDateExecution(Date dateExecution, long codeAO) {
 		aoRepo.changeDateExecution(dateExecution, codeAO);
 	}
 	
 	public void changeDossierConsultation(MultipartFile file, long codeAO) {
 		String dossierConsultationAO = file.getOriginalFilename();
 		try {
 			aoRepo.changeDossierConsultation(dossierConsultationAO, file.getContentType(),file.getBytes(), codeAO);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	public Optional<AppelOffres> getAO(Long codeAO) { 
 		return aoRepo.findById(codeAO); 
 	}
 	
 	public List<AppelOffres> getAOs(){
 		return aoRepo.findAll();
 	}
 	
 	public void deleteAO(Long id) {
 		aoRepo.deleteById(id);
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
