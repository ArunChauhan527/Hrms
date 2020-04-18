package com.hrms.Hrms.service;

import com.hrms.Hrms.model.Registration;

public interface LoginService {

	public Registration getLogin(String username,String password);
	
	String getRegister(Registration reg);

}
