package com.hrms.Hrms.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer>{

	
	@Query("Select a from Admin a where industry=:company")
	Admin getleavePolicy(@Param("company")String company);
	
	
	Page<Admin> findByIndustry(String industry, Pageable page);
	
	Admin findByRoleIdAndIndustry(int roleId, String industry);
	
	Admin findByIndustryAndRoleName(String industry, String roleName);
	
	Page<Admin> findByIndustryAndRoleNameContains(String industry, String roleName, Pageable page);
	
	Admin findByDefaultRoleTrue();
}
