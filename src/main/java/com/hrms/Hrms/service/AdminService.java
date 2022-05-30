package com.hrms.Hrms.service;

import java.util.List;

import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.model.ClientInfo;
import com.hrms.Hrms.model.MenuItem;

public interface AdminService {

	
	Admin save(Admin admin);
	
	void delete(int id);
	
	Admin findById(int Sno);
	
	List<Admin> findByIndustry(String industry);
	
	List<Admin> findByRoleAndIndustry(String role, String industry);
	
	List<MenuItem> save(List<MenuItem> menuList);
	
	List<MenuItem> findAll();
	
	ClientInfo save(ClientInfo clientInfo);
	
}
