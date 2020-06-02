package com.example.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AnnonceRepository;
import com.example.entities.Annonce;

@Service
@Transactional
public class AnnonceMetier {
	@Autowired
	private AnnonceRepository annonceRepo;
	
	public List<Annonce> getAnnonces() {
		return annonceRepo.findAll();
	}
	
	public Page<Annonce> findAll(Pageable pageable){
		return this.annonceRepo.findAll(pageable);
	}
	
	public List<Annonce> findLatest5(){
		return annonceRepo.findLatest5Annonces(PageRequest.of(0,5));
	}

	public Annonce findById(Long id) {
		return this.annonceRepo.findById(id).get();
	}

	public Annonce saveAnnonce(Annonce annonce) {
 		try {
 			return annonceRepo.save(annonce);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		return null;
 	}

	public void deleteById(Long id) {
		annonceRepo.deleteById(id);
	}

}
