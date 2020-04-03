package com.hrms.Hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.repository.LoginRepository;

@Service(value="login")
public class LoginServiceImpl  implements LoginService{

	@Autowired
	LoginRepository loginRepository;
	
	@Override
	public Registration getLogin(String username, String password) {
		// TODO Auto-generated method stub
		Registration res =null;
		try{
			
			 res = loginRepository.getLogin(username, password);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res;
	}

	
	
}
