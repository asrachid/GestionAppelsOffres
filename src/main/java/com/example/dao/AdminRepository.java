package com.example.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Admin;


public interface AdminRepository extends JpaRepository<Admin,Long>{
	@Query("select admin from Admin admin where admin.email=:email")
 	public Admin getAdminByEmail(@Param("email") String email);
}
