package com.hrms.Hrms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.service.EmailServiceImpl;

@RestController
public class TestController {

	@Autowired
	EmailServiceImpl email;
	
	@Autowired
	JwtDetailsService jwt;
	
	@GetMapping("/FirstApp")
	public ResponseEntity<?> FirstOne()
	{
	   	System.out.println("Security Context"+SecurityContextHolder.getContext().getAuthentication().getName());
		
		return ResponseEntity.status(HttpStatus.OK).body("hello");
	}
	
	@PostMapping("/testHash")
	public ResponseEntity<?> testHash() throws JsonParseException, JsonMappingException, IOException
	{
	 JwtDetials jwtDetails =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	System.out.println(jwtDetails+" /// "+ jwtDetails.toString());
	 
	List<HashMap<String,Object>> map = new ArrayList<>();
	HashMap<String, Object> test = new HashMap<>();
	test.put("type",1);
	
	map.add(test);
	
	
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	@GetMapping("/mail")
	public void mailTest(){
		
		email.sendSimpleMessage("vikash.singh.mind@gmail.com", "Sample mail from Hrms Application", "This is test mail to check mail   \r \n Thanks&Regards \n Arun Chuhan");
		
	}
	
}
