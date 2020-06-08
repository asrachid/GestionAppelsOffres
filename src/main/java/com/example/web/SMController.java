package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Annonce;
import com.example.entities.AppelOffres;
import com.example.entities.Soumissionnaire;
import com.example.metier.AnnonceMetier;
import com.example.metier.AppelOffresMetier;
import com.example.metier.SoumissionMetier;
import com.example.metier.UtilisateurMetier;

@Controller
public class SMController {
	@Autowired
	private UtilisateurMetier userMetier;
	@Autowired
	private AppelOffresMetier aoMetier;
	@Autowired
	private AnnonceMetier annonceMetier;
	@Autowired
	private SoumissionMetier soumissionMetier;
	
	
	@RequestMapping(value="/soumissionner")
	public String soumissionner(Model model, @ModelAttribute("codeAO") Long codeAO, @RequestParam(value="info", required=false) String info, @RequestParam(value="exception", required=false) String exception) {
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		model.addAttribute("ao",ao);
		
		model.addAttribute("info", info);
		model.addAttribute("exception", exception);
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		return "soumissionner";
	}
	
	@RequestMapping(value="/saveSoumission", method = RequestMethod.POST)
	public ModelAndView saveSoumission(ModelMap model, String emailSM, Long codeAO, MultipartFile file) {
		try {
			AppelOffres ao = aoMetier.getAO(codeAO).get();
			Soumissionnaire sm = userMetier.getSMByEmail(emailSM);
			soumissionMetier.saveSoumission(ao, sm, file);
			model.addAttribute("info","Soumission validé!");
		}catch(Exception e) {
			model.addAttribute("exception",e.getMessage());
		}
		return new ModelAndView("redirect:/soumissionner"+"?codeAO="+codeAO, model);
	}
}
