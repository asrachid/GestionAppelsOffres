package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long>{

}
