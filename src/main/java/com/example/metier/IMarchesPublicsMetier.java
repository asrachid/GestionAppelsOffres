package com.example.metier;


import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.entities.AppelOffres;
import com.example.entities.Soumissionnaire;
import com.example.entities.Utilisateur;


public interface IMarchesPublicsMetier {
	
	public Optional<Utilisateur> consulterUtilisateur(Long codeUser);
	public void Soumissionner(Long codeAO,Long codeUser);
	public Page<AppelOffres> listAppelOffresAP(Long codeUser,int page,int size);
	public Page<AppelOffres> listAppelOffresSecteur(String secteur,int page,int size);
	Optional<Utilisateur> findByIdUser(Long codeUser);
	Optional<AppelOffres> findByIdAO(Long codeAO);
}
