package com.hrms.Hrms.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.Otp;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.repository.LoginRepository;
import com.hrms.Hrms.repository.OtpRepository;

import lombok.extern.slf4j.Slf4j;

@Service(value="login")
@Slf4j
public class LoginServiceImpl  implements LoginService, UserDetailsService{

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
			log.error("error while login {}",e.getMessage());
		}
		
		return res;
	}

	@Override
	public String getRegister(Registration reg) {
		
		try {
			
			loginRepository.save(reg);
			return "success";
		}catch (Exception e) {
			log.error("error while registering {}", e.getMessage());
             return e.getMessage();
		}
		
	}

	@Override
	public String genrateOtp(Otp otp) {
		try{
           otprepository.save(otp);
           return "success";
			
		}
		catch (Exception e) {
			log.error("error while genrateOtp {}",e.getMessage());
			return "error";
		}
		
	}

	@Override
	public String changePassword(HashMap<String, Object> map) {
		
		try{
			String password = (String) map.get("password");
			String emailid = (String) map.get("emailid");
			String otp = (String) map.get("otp");
			
			loginRepository.changePassword(password, emailid);
			otprepository.changePassword(emailid,otp);
			return "success";
		}catch (Exception e) {
			log.error("error while changePassword {}", e.getMessage());
			return "error";
		}
		
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Registration users  = loginRepository.findByUserName(username);
		
		if(users!=null)
		{
			log.info("user is avialable {}", users.getUserName());
			return new User(users.getUserName(), users.getPassword(), new ArrayList<>());
		}
		else
		{
		 throw new UsernameNotFoundException(username +" is not present");
		}
	}
	

	
	
}
