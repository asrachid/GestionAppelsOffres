package com.example.metier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AppelOffresRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.AppelOffres;
import com.example.entities.Soumissionnaire;
import com.example.entities.Utilisateur;

@Service
@Transactional
public class MarchesPublicsMetierImpl implements IMarchesPublicsMetier{
	
	@Autowired
	private AppelOffresRepository appelOffresRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private UtilisateurRepository soumissionnaireRepository;
	
	@Override
	public Optional<Utilisateur> consulterUtilisateur(Long codeUser) {
		Optional <Utilisateur> user = utilisateurRepository.findById(codeUser);
        if(user==null) throw new RuntimeException("utilisateur Introuvable!");
        return user;
	}
	
	
	
	@Override
	public Page<AppelOffres> listAppelOffresAP(Long codeUser, int page, int size) {
		return appelOffresRepository.listAppelOffresAP(codeUser,PageRequest.of(page,size));
	}

	@Override
	public Page<AppelOffres> listAppelOffresSecteur(String secteur, int page, int size) {
		return appelOffresRepository.listAppelOffresSecteur(secteur,PageRequest.of(page,size));
	}

	@Override
	public Optional<AppelOffres> findByIdAO(Long codeAO) {
		return appelOffresRepository.findById(codeAO);
	}
	
	
	
	@Override
	public void Soumissionner(Long codeAO, Long codeUser) {
		if(findByIdAO(codeAO).isPresent() && findByIdUser(codeUser).isPresent()){
	        /*Optional<Utilisateur> sm = findByIdUser(codeUser);
	        Optional<AppelOffres> ao = findByIdAO(codeAO);*/
	        AppelOffres ao=appelOffresRepository.getOne(codeAO);
	        Soumissionnaire sm=(Soumissionnaire) soumissionnaireRepository.getOne(codeUser);
			ao.getSoumissionnaires().add(sm);
			sm.getAppelsOffres().add(ao);
			appelOffresRepository.save(ao);
	        
		}
		
	}
	
	
	@Override
	public Optional<Utilisateur> findByIdUser(Long codeUser) {
		return utilisateurRepository.findById(codeUser);
	}
	
}
