package com.example.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Document;


public interface DocumentRepository extends JpaRepository<Document,Long> {
	@Query("select count(*) from Document")
 	public Long nbreDocs();
	
	@Query("select doc from Document doc where doc.nomDoc=:nomDoc ")
 	public Document getDocInscription(@Param("nomDoc") String nomDoc);
	
}
