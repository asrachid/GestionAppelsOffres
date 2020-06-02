package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Soumissionnaire;

@Repository
public interface SoumissionnaireRepository extends JpaRepository<Soumissionnaire,Long>{
	/*
	 * @Query("select sm from Soumissionnaire sm where sm.codeUser=:x in (select sm.codeUser from soumissionner where AppelOffres.codeAO=:y)"
	 * ) public Page<AppelOffres> listAppelOffresSecteur(@Param("x") Long codeUser
	 * , @Param("y") Long codeAO , Pageable pageable );
	 */
	@Query("select count(*) from Soumissionnaire")
 	public Integer nbreSM();
}
