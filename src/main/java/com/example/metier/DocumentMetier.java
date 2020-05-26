package com.example.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DocumentRepository;
import com.example.entities.Document;

@Service
@Transactional
public class DocumentMetier {
	@Autowired
	private DocumentRepository docRepo;
	
 	public Document saveFile(MultipartFile file) {
 		String nomDoc = file.getOriginalFilename();
 		try {
 			Document doc = new Document(nomDoc,file.getContentType(),file.getBytes());
 			return docRepo.save(doc);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		return null;
 	}
 	
 	public Optional<Document> getFile(Long fileId) {
 		return docRepo.findById(fileId);
 	}
 	
 	public List<Document> getFiles(){
 		return docRepo.findAll();
 	}
 	
 	public void deleteDoc(Long id) {
 		docRepo.deleteById(id);
	}
}
