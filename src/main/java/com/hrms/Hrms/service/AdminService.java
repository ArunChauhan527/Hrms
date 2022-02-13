package com.hrms.Hrms.service;

import java.util.List;

import com.hrms.Hrms.model.Admin;

public interface AdminService {

	
	Admin save(Admin admin);
	
	void delete(int id);
	
	Admin findById(int Sno);
	
	List<Admin> findByIndustry(String industry);
	
	List<Admin> findByRoleAndIndustry(String role, String industry);
	
	
}
