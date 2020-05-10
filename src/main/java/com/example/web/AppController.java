package com.example.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entities.AppelOffres;
import com.example.entities.Utilisateur;
import com.example.metier.IMarchesPublicsMetier;

@Controller
public class AppController {
	@Autowired
	private IMarchesPublicsMetier metier;
	
	
	@RequestMapping(value="/")
	public String index(Model model , @ModelAttribute("selectedSecteur") AppelOffres selectedSecteur) {
		List<AppelOffres> listAOs = metier.listAll();
		model.addAttribute("listAOs", listAOs);
		
		List<AppelOffres> listAOSecteur = metier.listAOBySecteur(selectedSecteur.getSecteurAO());
		model.addAttribute("listAOSecteur", listAOSecteur);
		
		ArrayList<String> listSecteurs = metier.listSecteurs();
		model.addAttribute("listSecteurs", listSecteurs);
		
		return "index";
	}
	
	@RequestMapping(value="/AuthAcheteurPublic")
	public String authAP(Model model , @ModelAttribute("user") Utilisateur user) {
		return "authAP";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String auth(Model model , @ModelAttribute("user") Utilisateur user) {
		String login=user.getLoginUser();
		Long code=user.getCodeUser();
		
		Utilisateur result=metier.getUser(code,login);
		if(result.getCodeUser()==code && result.getLoginUser()==login) {
			return "portailAP";
			//model.addAttribute("succes","Authentification reussi");
		}else {
			model.addAttribute("exception","Login ou mot de passe incorrecte");
		}
		return "authAP";
	}
}
