package com.hrms.Hrms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.repository.AdminRepository;

@Service
public class AdminServiceImpl  implements AdminService{

	
	@Autowired
	AdminRepository adminRep;
	
	@Override
	public Admin save(Admin admin) {
	return	adminRep.save(admin);
	}

	@Override
	public void delete(int id) {
	 adminRep.deleteById(id);	
	}

	@Override
	public Admin findById(int Sno) {
		Optional<Admin>  admin= adminRep.findById(Sno);
		if(admin.isPresent())
		{
			return admin.get();
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<Admin> findByIndustry(String industry) {
		return adminRep.findByIndustry(industry);
	}

	@Override
	public List<Admin> findByRoleAndIndustry(String role, String industry) {
		return adminRep.findByRoleAndIndustry(role, industry);
	}

	
	
}
