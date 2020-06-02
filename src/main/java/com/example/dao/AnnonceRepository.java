package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Annonce;


public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
	@Query("select annonce from Annonce annonce order by annonce.date desc")
	public List<Annonce> findLatest5Annonces(Pageable pageable);
}
