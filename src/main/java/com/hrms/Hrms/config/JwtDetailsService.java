package com.hrms.Hrms.config;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.Hrms.Dto.JwtDetials;

@Service
public class JwtDetailsService {

	public JwtDetials getJwtDetails(UserDetails users)  {
	  JwtDetials jwt = new JwtDetials();
	try {
		jwt = new ObjectMapper().readValue(users.getAuthorities().iterator().next().getAuthority(), JwtDetials.class);
	} catch (IOException e) {
		e.printStackTrace();
	}
		return jwt;
	}
	
	
}
