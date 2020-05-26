package com.example.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.AcheteurPublic;
import com.example.entities.AppelOffres;

@Repository
public interface AppelOffresRepository extends JpaRepository<AppelOffres,Long>{
	
	@Query("select ao from AppelOffres ao order by ao.dateAO desc")
	public List<AppelOffres> listAll();
	
	@Query("select ao from AppelOffres ao where ao.secteurAO=:secteurAO order by ao.dateAO desc")
 	public List<AppelOffres> listAOBySecteur(@Param("secteurAO") String secteurAO);
	
	@Query("select ao from AppelOffres ao where ao.acheteurPublic=:acheteurPublic and ao.secteurAO=:secteurAO order by ao.dateAO desc")
 	public List<AppelOffres> listAOByAPSec(@Param("acheteurPublic") AcheteurPublic acheteurPublic, @Param("secteurAO") String secteurAO);
	
	@Query("select distinct secteurAO from AppelOffres")
 	public ArrayList<String> listSecteurs();
	
	@Modifying
	@Query("update AppelOffres ao set ao.dateLimiteRemisePlis=:dateLimiteRemisePlis where ao.codeAO=:codeAO")
 	public void changeDateLimiteRemisePlis(@Param("dateLimiteRemisePlis") Date dateLimiteRemisePlis,@Param("codeAO") long codeAO);
	
	@Modifying
	@Query("update AppelOffres ao set ao.lieuExecution=:lieuExecution where ao.codeAO=:codeAO")
 	public void changeLieuExecution(@Param("lieuExecution") String lieuExecution,@Param("codeAO") long codeAO);
	
	@Modifying
	@Query("update AppelOffres ao set ao.dateExecution=:dateExecution where ao.codeAO=:codeAO")
 	public void changeDateExecution(@Param("dateExecution") Date dateExecution,@Param("codeAO") long codeAO);
	
	@Modifying
	@Query("update AppelOffres ao set ao.dossierConsultationAO=:dossierConsultationAO, ao.typeDocConsultation=:typeDocConsultation, ao.data=:data where ao.codeAO=:codeAO")
 	public void changeDossierConsultation(@Param("dossierConsultationAO") String dossierConsultationAO,@Param("typeDocConsultation") String typeDocConsultation,@Param("data") byte[] data,@Param("codeAO") long codeAO);
	
	
}
