package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Document;


public interface DocumentRepository extends JpaRepository<Document,Long> {
	@Query("select count(*) from Document")
 	public Long nbreDocs();
}
