package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.AcheteurPublic;


public interface APRepository extends JpaRepository<AcheteurPublic,Long>{
	@Query("select ap from AcheteurPublic ap where ap.email=:email")
 	public AcheteurPublic getAPByEmail(@Param("email") String email);
	
	@Modifying
	@Query("update AcheteurPublic ap set ap.profileAP=:profileAP where ap.id=:id")
 	public void changeProfilAP(@Param("profileAP") String profileAP,@Param("id") long id);
	
	@Modifying
	@Query("update AcheteurPublic ap set ap.type=:type where ap.id=:id")
 	public void changeTypeAP(@Param("type") String type,@Param("id") long id);
}
