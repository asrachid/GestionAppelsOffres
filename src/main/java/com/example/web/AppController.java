package com.example.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entities.Annonce;
import com.example.entities.AppelOffres;
import com.example.entities.Document;
import com.example.metier.AnnonceMetier;
import com.example.metier.AppelOffresMetier;
import com.example.metier.DocumentMetier;

@Controller
public class AppController {
	@Autowired
	private AppelOffresMetier aoMetier;
	@Autowired
	private DocumentMetier docMetier;
	
	@Autowired
	private AnnonceMetier annonceMetier;
	
	@RequestMapping(value = "/appelOffres")
	public String index(Model model , @ModelAttribute("selectedSecteur") AppelOffres selectedSecteur) {
		List<AppelOffres> listAOs = aoMetier.listAll();
		model.addAttribute("listAOs", listAOs);
		
		List<AppelOffres> listAOSecteur = aoMetier.listAOBySecteur(selectedSecteur.getSecteurAO());
		Date today = new Date();
		model.addAttribute("today", today);
		model.addAttribute("listAOSecteur", listAOSecteur);

		ArrayList<String> listSecteurs = aoMetier.listSecteurs();
		model.addAttribute("listSecteurs", listSecteurs);
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		
		return "accueil";
	}
	
	@GetMapping("/docsOff")
	public String visualiserDocs(Model model) {
		List <Document> docs = docMetier.getFiles();
		model.addAttribute("docs", docs);
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		return "docsOff";
	}
	
	@RequestMapping(value = "/viewAnnonce/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewAnnonce(Model model,  @PathVariable(name = "id") Long id) {
		Annonce annonce = annonceMetier.findById(id);
		model.addAttribute("annonce", annonce);
		
		List<Annonce> annonces = annonceMetier.getAnnonces();
		model.addAttribute("annonces", annonces);
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		return "viewAnnonce";
	}
	
	@GetMapping(value="/login")
    public String loginPage(){		
        return "login";
    }
	
}
