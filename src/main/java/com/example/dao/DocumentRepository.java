package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Document;


public interface DocumentRepository extends JpaRepository<Document,Long> {

}
