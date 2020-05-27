package com.hrms.Hrms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.Otp;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.repository.LoginRepository;
import com.hrms.Hrms.repository.OtpRepository;

@Service(value="login")
public class LoginServiceImpl  implements LoginService{

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	OtpRepository otprepository;
	
	
	@Override
	public Registration getLogin(String username, String password) {
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

	@Override
	public String getRegister(Registration reg) {
		
		try {
			
			loginRepository.save(reg);
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
             return e.getMessage();
		}
		
	}

	@Override
	public String genrateOtp(Otp otp) {
		// TODO Auto-generated method stub
		try{
           otprepository.save(otp);
           return "success";
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
		
	}

	@Override
	public String changePassword(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
		try{
			String password = (String) map.get("password");
			String emailid = (String) map.get("emailid");
			String otp = (String) map.get("otp");
			
			loginRepository.changePassword(password, emailid);
			otprepository.changePassword(emailid,otp);
			return "success";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		
		
	}
	

	
	
}
