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

import com.example.dao.AppelOffresRepository;
import com.example.entities.AcheteurPublic;
import com.example.entities.AppelOffres;

@Service
@Transactional
public class AppelOffresMetier {
	@Autowired
	private AppelOffresRepository aoRepo;
	
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
}
