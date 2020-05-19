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
import com.example.metier.MarchesPublicsMetier;

@Controller
public class AppController {
	@Autowired
	private MarchesPublicsMetier metier;

	@RequestMapping(value = "/appelOffres")
	public String index(Model model , @ModelAttribute("selectedSecteur") AppelOffres selectedSecteur) {
		List<AppelOffres> listAOs = metier.listAll();
		model.addAttribute("listAOs", listAOs);
		
		List<AppelOffres> listAOSecteur = metier.listAOBySecteur(selectedSecteur.getSecteurAO());
		model.addAttribute("listAOSecteur", listAOSecteur);

		ArrayList<String> listSecteurs = metier.listSecteurs();
		model.addAttribute("listSecteurs", listSecteurs);
		
		return "accueil";
	}
	
	@GetMapping(value="/login")
    public String loginPage(){		
        return "login";
    }

}
