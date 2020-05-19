package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.AppelOffres;
import com.example.metier.MarchesPublicsMetier;

@Controller
public class APController {
	@Autowired
	private MarchesPublicsMetier metier;
	
	@RequestMapping(value="/portailAP")
    public String pageAP(){
        return "portailAP";
    }
	
	@GetMapping("/gestionAO")
	public String get(Model model) {
		List <AppelOffres> aos = metier.getAOs();
		model.addAttribute("aos", aos);
		return "aos";
	}
	
}
