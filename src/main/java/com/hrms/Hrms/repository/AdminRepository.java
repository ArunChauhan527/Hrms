package com.hrms.Hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer>{

	
	@Query("Select a from Admin a where industry=:company")
	Admin getleavePolicy(@Param("company")String company);
	
	
	List<Admin> findByIndustry(String industry);
	
	List<Admin> findByRoleAndIndustry(String role, String industry);
	
}
