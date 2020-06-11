package com.example.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.AcheteurPublic;
import com.example.entities.Annonce;
import com.example.entities.AppelOffres;
import com.example.entities.Soumission;
import com.example.metier.AnnonceMetier;
import com.example.metier.AppelOffresMetier;
import com.example.metier.SoumissionMetier;
import com.example.metier.UtilisateurMetier;

@Controller
public class APController {
	@Autowired
	private UtilisateurMetier userMetier;
	@Autowired
	private AppelOffresMetier aoMetier;
	@Autowired
	private AnnonceMetier annonceMetier;
	@Autowired
	private SoumissionMetier soumissionMetier;
	
	@RequestMapping(value = "/portailAP")
	public String pageAP(Model model) {
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		return "portailAP";
	}
	
	@RequestMapping(value = "/portailAP/{email}", method = { RequestMethod.GET, RequestMethod.POST })
	public String evaluatePortailAP(Model model,  @PathVariable(name = "email") String email) {
		AcheteurPublic ap = userMetier.getAPByEmail(email);
		model.addAttribute("ap", ap);
		Long nbreAOsAP = aoMetier.nbreAOsAP(ap);
		model.addAttribute("nbreAOsAP", nbreAOsAP);
		
		Integer nbreTravaux = aoMetier.nbreAOSecteur("Travaux");
		Integer nbreFournitures = aoMetier.nbreAOSecteur("Fournitures");
		Integer nbreServices = aoMetier.nbreAOSecteur("Services");
		Map<String,Integer> surveyMap = new HashMap<>();
		if(nbreAOsAP!=0) {
			Integer pctTravaux = (int) ((nbreTravaux*100)/nbreAOsAP);
			Integer pctFournitures = (int) ((nbreFournitures*100)/nbreAOsAP);
			Integer pctServices = (int) ((nbreServices*100)/nbreAOsAP);
			
			surveyMap.put("Travaux", pctTravaux);
			surveyMap.put("Fournitures", pctFournitures);
			surveyMap.put("Services", pctServices);
			model.addAttribute("surveyMap",surveyMap);
		}else {
			surveyMap.put("Travaux", 0);
			surveyMap.put("Fournitures", 0);
			surveyMap.put("Services", 0);
			model.addAttribute("surveyMap",surveyMap);
		}
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		
		return "evaluatePortailAP";
	}
	
	@RequestMapping(value = "/gestionAO/{email}", method = { RequestMethod.GET, RequestMethod.POST })
	public String gestionAO(Model model,  @PathVariable(name = "email") String email,  @ModelAttribute("selectedSecteur") AppelOffres selectedSecteur) {
		AcheteurPublic ap = userMetier.getAPByEmail(email);
		model.addAttribute("ap", ap);


		ArrayList<String> listSecteurs = aoMetier.listSecteurs();
		model.addAttribute("listSecteurs", listSecteurs);
		
		List<AppelOffres> listAoAPSec = aoMetier.listAOByAPSec(ap, selectedSecteur.getSecteurAO());
		Date today = new Date();
		model.addAttribute("listAoAPSec", listAoAPSec);
		model.addAttribute("today", today);
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		
		return "gestionAO";
	}
	
	@GetMapping("/downloadDoc/{aoId}")
	public ResponseEntity<ByteArrayResource> downloadDoc(@PathVariable Long aoId) {
		AppelOffres doc = aoMetier.getAO(aoId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getTypeDocConsultation())) 
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+doc.getDossierConsultationAO()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	@RequestMapping(value = "/addAO/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String addAO(Model model,  @PathVariable(name = "id") Long id, @ModelAttribute("ao")AppelOffres ao) {
		AcheteurPublic ap = userMetier.getAP(id);
		model.addAttribute("ap",ap);
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		
		return "addAO";
	}

	/* @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) */
	@RequestMapping(value="/saveAO", method = RequestMethod.POST)
	public String saveAO(String objetAO, String categorieAO, String secteurAO, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateLimiteRemisePlis,
			String lieuExecution, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateExecution, Long idAP, MultipartFile file) {
		AcheteurPublic ap = userMetier.getAP(idAP);
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-Y HH-mm");
		Date dateLimiteRemisePlis = sdf.parse(dateLimiteRemisePlis);*/
		aoMetier.saveAO(objetAO, categorieAO, secteurAO, dateLimiteRemisePlis, dateExecution, lieuExecution, ap, file);
		
		return "redirect:/gestionAO/"+ap.getEmail();
	}
	
	@RequestMapping(value="/changeDateLimiteRemisePlis" ,method = RequestMethod.POST )
    public String changeDateLimiteRemisePlis(Model model, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateLimiteRemisePlis, Long idAP, Long codeAO) {
		AcheteurPublic ap = userMetier.getAP(idAP);
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		try { 
			aoMetier.changeDateLimiteRemisePlis(dateLimiteRemisePlis, codeAO);
		}catch (Exception e){ 
			model.addAttribute("error",e); 
			return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO()+"&error="+e.getMessage(); 
		}
	    return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO();
		
	}

	@RequestMapping(value="/changeLieuExecution" ,method = RequestMethod.POST )
    public String changeLieuExecution(Model model, String lieuExecution, Long idAP, Long codeAO) {
		AcheteurPublic ap = userMetier.getAP(idAP);
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		try { 
			aoMetier.changeLieuExecution(lieuExecution, codeAO);
		}catch (Exception e){ 
			model.addAttribute("error",e); 
			return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO()+"&error="+e.getMessage(); 
		}
	    return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO();
		
	}
	
	@RequestMapping(value="/changeDateExecution" ,method = RequestMethod.POST )
    public String changeDateExecution(Model model, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateExecution, Long idAP, Long codeAO) {
		AcheteurPublic ap = userMetier.getAP(idAP);
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		try { 
			aoMetier.changeDateExecution(dateExecution, codeAO);
		}catch (Exception e){ 
			model.addAttribute("error",e); 
			return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO()+"&error="+e.getMessage(); 
		}
	    return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO();
		
	}
	
	@RequestMapping(value="/changeDossierConsultation" ,method = RequestMethod.POST )
    public String changeDossierConsultation(Model model, MultipartFile dossierConsultation, Long idAP, Long codeAO) {
		AcheteurPublic ap = userMetier.getAP(idAP);
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		try { 
			aoMetier.changeDossierConsultation(dossierConsultation, codeAO);
		}catch (Exception e){ 
			model.addAttribute("error",e); 
			return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO()+"&error="+e.getMessage(); 
		}
	    return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+ao.getSecteurAO();
		
	}
	
	@RequestMapping("/editAO/{codeAO}&{id}")
	public ModelAndView editAO(@PathVariable(name = "codeAO") Long codeAO, @PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("editAO");
		AcheteurPublic ap = userMetier.getAP(id);
		mav.addObject("ap", ap);
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		mav.addObject("ao", ao);
		List<Annonce> latest = annonceMetier.findLatest5();
		mav.addObject("latest", latest);
		return mav;
	}
	
	@RequestMapping("/deleteAO/{codeAO}&{id}")
	public String deleteAO(@PathVariable(name = "codeAO") Long codeAO, @PathVariable(name = "id") Long id) {
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		String secteur = ao.getSecteurAO();
		aoMetier.deleteAO(codeAO);
		AcheteurPublic ap = userMetier.getAP(id);
		return "redirect:/gestionAO/"+ap.getEmail()+"?secteurAO="+secteur;
	}
	
	@RequestMapping(value="/viewSoumissions")
	public String viewSoumissions(Model model, @ModelAttribute("codeAO") Long codeAO) {
		AppelOffres ao = aoMetier.getAO(codeAO).get();
		model.addAttribute("ao", ao);
		List<Soumission> soumissions = soumissionMetier.listSoumissionsByAO(ao);
		model.addAttribute("soumissions", soumissions);
		
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		return "viewSoumissions";
	}
	
	
	@GetMapping("/downloadDocCandidature/{smsId}")
	public ResponseEntity<ByteArrayResource> downloadDocCandidature(@PathVariable Long smsId) {
		Soumission doc = soumissionMetier.getSoumission(smsId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getTypeDoc())) 
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+doc.getDossierCandidature()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	@RequestMapping(value = "/profilAP/{email}")
	public String profilAP(Model model, @PathVariable(name = "email") String email) {
		AcheteurPublic ap = userMetier.getAPByEmail(email);
		model.addAttribute("ap", ap);
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		return "profilAP";
	}
	
	@RequestMapping(value="/editProfilAP/{id}") 
	public ModelAndView editprofilAP(Model model, @PathVariable(name = "id") Long id) { 
		ModelAndView mav = new ModelAndView("editProfilAP");
	  
		AcheteurPublic ap = userMetier.getAP(id); 
		mav.addObject("ap", ap);
	  
		List<Annonce> latest = annonceMetier.findLatest5();
		model.addAttribute("latest", latest);
		
		return mav; 
	}
	  
	@RequestMapping(value="/saveProfilAP", method = RequestMethod.POST) 
	public String saveProfilAP(@ModelAttribute("ap")AcheteurPublic ap) {
		userMetier.saveAP(ap);
		return "redirect:/profilAP/"+ap.getEmail(); 
	}
	 
}
