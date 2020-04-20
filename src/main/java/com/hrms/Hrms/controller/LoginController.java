package com.hrms.Hrms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	@Qualifier(value = "login")
	LoginService login;

	@PostMapping(value = "loginDB")
	public ResponseEntity getlogin(@RequestBody String Data) throws JsonProcessingException {
		try {
			HashMap<String, String> response = new HashMap<>();

			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, String> request = mapper.readValue(Data, HashMap.class);
			String username = request.get("username");
			if (username == null)
				username = "";
			String password = request.get("password");
			if (password == null)
				password = "";

			if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
				response.put("message", "Username and password can't be empty");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(response));
			} else {
                       Registration resg =login.getLogin(username, password);
                       String responseOut = mapper.writeValueAsString(login.getLogin(username, password));
                       if(resg==null)
                       {
                    	   response.put("message","username or password is wrong");
                    	   responseOut = mapper.writeValueAsString(response);
                       }
                       
                       System.err.println(responseOut);
                       
				return ResponseEntity.status(HttpStatus.OK)
						.body(responseOut);
			}

		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, String> response = new HashMap<>();
			response.put("message", "Username and password can't be empty");
			ObjectMapper mapper = new ObjectMapper();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(response));
		}

	}

	@PostMapping(value = "register")
	public ResponseEntity resgister(@RequestBody String data) throws JsonProcessingException {
		HashMap<String, Object> regis = new HashMap<>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			
			Registration res = mapper.readValue(data, Registration.class);
			
			
			String msg = login.getRegister(res);
			
			if(msg.equalsIgnoreCase("success"))
			{
				regis.put("message", "Saved successfully");
				return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(regis));
			}
			else
			{
				
				regis.put("message", msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(regis));
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			regis.put("message",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(regis));
		}
		
	}
}
