package com.example.metier;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.AppelOffres;
import com.example.entities.Soumissionnaire;
import com.example.entities.Utilisateur;


public interface IMarchesPublicsMetier {
	
	/*
	 * public Optional<Utilisateur> consulterUtilisateur(Long codeUser); public void
	 * Soumissionner(Long codeAO,Long codeUser); public Page<AppelOffres>
	 * Optional<Utilisateur> findByIdUser(Long codeUser); Optional<AppelOffres>
	 * findByIdAO(Long codeAO);
	 */
	public List<AppelOffres> listAll();
 	public List<AppelOffres> listAOBySecteur(@Param("secteurAO") String secteurAO);
 	public ArrayList<String> listSecteurs();
	public Utilisateur getUser(@Param("code") Long codeUser , @Param("login") String loginUser);
}
