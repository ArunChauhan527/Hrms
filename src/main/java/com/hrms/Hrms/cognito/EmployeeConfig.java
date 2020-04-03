package com.hrms.Hrms.cognito;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface EmployeeConfig {

	public String Signup(String request);
	
	public Map<String, Object> login(String request) throws JsonParseException, JsonMappingException, IOException;
	
	public String confirmEmail(String otp,String username);
}
