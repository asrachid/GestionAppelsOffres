package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Utilisateur;
import com.example.metier.UtilisateurMetier;

@Controller
public class SimpleEmailController {
	
	@Autowired
	public JavaMailSender javaMailSender;
	@Autowired
	private UtilisateurMetier userMetier;
	
	@RequestMapping(value = "/sendEmail")
	public ModelAndView sendEmail(ModelMap model, @ModelAttribute("role") String role, @ModelAttribute("id") Long id) {
		try {
			Utilisateur user = userMetier.getUser(id);
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(user.getEmail());
			message.setSubject("Vos coordonnées d'accès");
			message.setText("Bienvenu sur notre plateforme,\n"
					+ "Vos coordonnées sont : \n"
					+ "Role : "+role
					+ "\nE-mail : "+user.getEmail()
					+ "\nCode d'accès : "+user.getPassword());
			
			javaMailSender.send(message);
			model.addAttribute("info","L'email à bien été envoyé!");
		}catch(Exception e) { 
			model.addAttribute("exception",e.getMessage()); 
		}
		 
        return new ModelAndView("redirect:/list"+role, model);
	}
	
}
