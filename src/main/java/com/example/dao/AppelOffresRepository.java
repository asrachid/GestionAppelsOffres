package com.example.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.AppelOffres;

@Repository
public interface AppelOffresRepository extends JpaRepository<AppelOffres,Long>{
	/*
	 * @Query("select ao from AppelOffres ao where ao.secteurAO=:x order by ao.dateAO desc "
	 * ) public Page<AppelOffres> listAppelOffresSecteur(@Param("x") String secteur
	 * , Pageable pageable );
	 * 
	 * @Query("select ao from AppelOffres ao where ao.acheteurPublic.codeUser=:x order by ao.dateAO desc "
	 * ) public Page<AppelOffres> listAppelOffresAP(@Param("x") Long codeUser ,
	 * Pageable pageable );
	 */
	@Query("select ao from AppelOffres ao order by ao.dateAO desc")
	public List<AppelOffres> listAll();
	
	@Query("select ao from AppelOffres ao where ao.secteurAO=:secteurAO order by ao.dateAO desc")
 	public List<AppelOffres> listAOBySecteur(@Param("secteurAO") String secteurAO);
	
	@Query("select distinct secteurAO from AppelOffres")
 	public ArrayList<String> listSecteurs();
	
	
}
