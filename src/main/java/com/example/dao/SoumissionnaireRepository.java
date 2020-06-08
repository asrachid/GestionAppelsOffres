package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Soumissionnaire;

@Repository
public interface SoumissionnaireRepository extends JpaRepository<Soumissionnaire,Long>{
	
	@Query("select sm from Soumissionnaire sm where sm.email=:email")
 	public Soumissionnaire getSMByEmail(@Param("email") String email);
	
	@Query("select count(*) from Soumissionnaire")
 	public Integer nbreSM();
}
