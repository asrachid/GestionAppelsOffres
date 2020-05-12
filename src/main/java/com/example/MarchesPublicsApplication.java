package com.example;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dao.AppelOffresRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.AcheteurPublic;
import com.example.entities.AppelOffres;
import com.example.entities.Soumissionnaire;

@SpringBootApplication
public class MarchesPublicsApplication implements CommandLineRunner {
	
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired
	private AppelOffresRepository aoRepo;

	public static void main(String[] args) {
		SpringApplication.run(MarchesPublicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		AcheteurPublic user1=userRepo.save(new AcheteurPublic("farid","farid","farid@gmail.com",true,"Administration de l'etat","DIRECTEUR PROVINCIAL DE L'EQUIPEMENT TAOUNATE"));
				 
		AppelOffres ao1=new AppelOffres("Travaux d’élargissement et de renforcement de la RR408 du PK 98+000 au PK 119+000, dépendant de la DPETLE de Taounate"
				 , "AOO","Travaux","procedure.pdf",new Date(),"dossierCandidature.pdf",new Date(),new Date(),"Taounat",user1);
				 
		AppelOffres ao2=new AppelOffres(" Fourniture de pré-imprimés divers pour la Direction Régionale Distribution de Marrakech"
				 , "AOO","Fournitures","procedure.pdf",new Date(),"dossierCandidature.pdf",new Date(),new Date(),"Marrakech",user1);
				  
		Soumissionnaire sm1=new Soumissionnaire("rachid","rachid", "rachid@gmail.com",true,"President de la commission d'appel d'offres de SEFIANI");
		Soumissionnaire sm2=new Soumissionnaire("asma","asma", "asma@gmail.com",true,"President de la commission d'appel d'offres de SEFIANI");
				 
		ao1.getSoumissionnaires().add(sm1); ao1.getSoumissionnaires().add(sm2);
				  
		ao2.getSoumissionnaires().add(sm2);
				 
		sm1.getAppelsOffres().add(ao1); sm2.getAppelsOffres().add(ao1);
		sm2.getAppelsOffres().add(ao2); this.aoRepo.save(ao1);
		*/
	}

}
