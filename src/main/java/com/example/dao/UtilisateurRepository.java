package com.example.dao;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long>{
	@Modifying
	@Query("update Utilisateur user set user.password=:password where user.id=:id")
 	public void changePassword(@Param("password") String password,@Param("id") long id);
	
	@Modifying
	@Query("update Utilisateur user set user.email=:email where user.id=:id")
 	public void changeEmail(@Param("email") String email,@Param("id") long id);
	
	@Modifying
	@Query("update Utilisateur user set user.active=:active where user.id=:id")
 	public void changeActive(@Param("active") Boolean active,@Param("id") long id);
	
	@Query("select count(*) from Utilisateur")
 	public Long nbreUsers();
}
