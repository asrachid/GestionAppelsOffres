package com.example.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long>{
	
	@Query("select user from Utilisateur user where user.codeUser=:code and user.loginUser=:login") 
	public Utilisateur getUser(@Param("code") Long codeUser , @Param("login") String loginUser);
	 
}
