package com.example.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.AppelOffres;
import com.example.metier.AppelOffresMetier;
import com.example.metier.DocumentMetier;
import com.example.metier.UtilisateurMetier;

@Controller
public class AppController {
	@Autowired
	private UtilisateurMetier userMetier;
	@Autowired
	private AppelOffresMetier aoMetier;
	@Autowired
	private DocumentMetier docMetier;

	@RequestMapping(value = "/appelOffres")
	public String index(Model model , @ModelAttribute("selectedSecteur") AppelOffres selectedSecteur) {
		List<AppelOffres> listAOs = aoMetier.listAll();
		model.addAttribute("listAOs", listAOs);
		
		List<AppelOffres> listAOSecteur = aoMetier.listAOBySecteur(selectedSecteur.getSecteurAO());
		model.addAttribute("listAOSecteur", listAOSecteur);

		ArrayList<String> listSecteurs = aoMetier.listSecteurs();
		model.addAttribute("listSecteurs", listSecteurs);
		
		return "accueil";
	}
	
	@GetMapping(value="/login")
    public String loginPage(){		
        return "login";
    }

}
