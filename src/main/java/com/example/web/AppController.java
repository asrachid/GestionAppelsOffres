package com.example.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value="/inscription")
	public String signUpPage(Model model) {
		
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		return "inscription";
	}
	
	
	@RequestMapping(value = "/downloadFormInscription")
	public ResponseEntity<ByteArrayResource> downloadFormInscription(Model model, @ModelAttribute("libele") String libele) {
		
		Document doc = docMetier.getDocInscription(libele+".doc");
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getTypeDoc()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+doc.getNomDoc()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	
	@GetMapping(value="/login")
    public String loginPage(){		
        return "login";
    }
	
}
