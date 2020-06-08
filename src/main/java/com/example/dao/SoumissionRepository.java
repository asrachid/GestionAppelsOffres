package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.AppelOffres;
import com.example.entities.Soumission;

@Repository
public interface SoumissionRepository extends JpaRepository<Soumission,Long> {
	@Query("select sms from Soumission sms where sms.appelOffres=:appelOffres")
 	public List<Soumission> listSoumissionsByAO(@Param("appelOffres") AppelOffres appelOffres);
	
}
