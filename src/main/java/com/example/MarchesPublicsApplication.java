package com.example;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dao.AppelOffresRepository;
import com.example.dao.MarcheRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.AcheteurPublic;
import com.example.entities.AppelOffres;
import com.example.entities.Marche;
import com.example.entities.MarcheKey;
import com.example.entities.Soumissionnaire;

@SpringBootApplication
public class MarchesPublicsApplication implements CommandLineRunner{
	
	@Autowired
	private AppelOffresRepository appelOffresRepository;
	@Autowired
	private MarcheRepository marcheRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MarchesPublicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		AcheteurPublic user1=utilisateurRepository.save(new AcheteurPublic(1130l,"Farid","Farid@gmail.com","Administration de l'etat","Administrateur Acheteur"));
		Soumissionnaire user2=utilisateurRepository.save(new Soumissionnaire(1134l,"Rachid","Rachid@gmail.com","President de la commission d'appel d'offres de SEFIANI"));
		
		AppelOffres AO1=appelOffresRepository.save(new AppelOffres("Travaux d’élargissement et de renforcement de la RR408 du PK 98+000 au PK 119+000, dépendant de la DPETLE de Taounate",
						"AOO","Travaux","procedure.pdf",new Date(),"dossierCandidature.pdf",user1));
		
		MarcheKey m1Key=new MarcheKey(AO1.getCodeAO(),user2.getCodeUser());
		
		marcheRepository.save(new Marche(m1Key,AO1,user2,new Date(),new Date(),"Taounat"));
		
	}

}
