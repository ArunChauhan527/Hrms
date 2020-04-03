package com.hrms.Hrms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.http.HttpResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hrms.Hrms.cognito.EmployeeConfig;

@RestController
public class CognitoController {

	
	@Autowired
	@Qualifier("EmployeeConfig")
	EmployeeConfig empployee;
	
	
	@PostMapping("/signUp")
	public ResponseEntity SignUp(@RequestBody String content)
	{
		empployee.Signup(content);
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("message", "signup successfully");
		return ResponseEntity.status(HttpStatus.OK).body(hashMap);
	}
	
	@PostMapping(value="/confirmsignUp")
	public ResponseEntity confirmSignUp(@RequestParam("otp") String otp,@RequestParam("username")String username)
	{
		
		empployee.confirmEmail(otp, username);
		HashMap<String,Object> hashMap = new HashMap<>();
		return ResponseEntity.status(HttpStatus.OK).body(hashMap.put("message", "signup successfully"));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> login(@RequestBody String content) throws JsonParseException, JsonMappingException, IOException
	{
		
		Map<String,Object> hashMap = new HashMap<>();
		hashMap = empployee.login(content);
		return ResponseEntity.status(HttpStatus.OK).body(hashMap);
	}
	
	
	
	
}
