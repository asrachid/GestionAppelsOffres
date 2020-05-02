package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Marche;
import com.example.entities.MarcheKey;


public interface MarcheRepository extends JpaRepository<Marche,MarcheKey>{

}
