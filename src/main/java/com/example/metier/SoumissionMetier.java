package com.example.metier;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.SoumissionRepository;
import com.example.entities.AppelOffres;
import com.example.entities.Soumission;
import com.example.entities.Soumissionnaire;


@Service
@Transactional
public class SoumissionMetier {
	@Autowired
	private SoumissionRepository soumissionRepo;
	
	public Optional<Soumission> getSoumission(Long smsId) { 
 		return soumissionRepo.findById(smsId); 
 	}
	
	public Soumission saveSoumission(AppelOffres ao, Soumissionnaire sm, MultipartFile file) {
 		String nomDoc = file.getOriginalFilename();
 		try {
 			Soumission soumission = new Soumission(ao, sm, nomDoc, file.getContentType(), file.getBytes());
 			return soumissionRepo.save(soumission);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		return null;
 	}
	
	public List<Soumission> listSoumissionsByAO(AppelOffres appelOffres){
		return soumissionRepo.listSoumissionsByAO(appelOffres);
	}
	
}
