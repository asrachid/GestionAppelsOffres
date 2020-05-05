package com.example.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.AppelOffres;

@Repository
public interface AppelOffresRepository extends JpaRepository<AppelOffres,Long>{
	@Query("select ao from AppelOffres ao where ao.secteurAO=:x order by ao.dateAO desc ")
 	public Page<AppelOffres> listAppelOffresSecteur(@Param("x") String secteur , Pageable pageable );
	
	@Query("select ao from AppelOffres ao where ao.acheteurPublic.codeUser=:x order by ao.dateAO desc ")
 	public Page<AppelOffres> listAppelOffresAP(@Param("x") Long codeUser , Pageable pageable );
}
