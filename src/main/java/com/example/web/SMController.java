package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.metier.AppelOffresMetier;
import com.example.metier.DocumentMetier;
import com.example.metier.UtilisateurMetier;

@Controller
public class SMController {
	@Autowired
	private UtilisateurMetier userMetier;
	@Autowired
	private AppelOffresMetier aoMetier;
	@Autowired
	private DocumentMetier docMetier;
}
