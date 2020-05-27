package com.hrms.Hrms.service;

import java.util.HashMap;

import com.hrms.Hrms.model.Otp;
import com.hrms.Hrms.model.Registration;

public interface LoginService {

	public Registration getLogin(String username,String password);
	
	String getRegister(Registration reg);
	
	String genrateOtp(Otp otp);
	
	String changePassword(HashMap<String, Object> map);

}
