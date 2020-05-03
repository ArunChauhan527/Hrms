package com.hrms.Hrms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.Hrms.model.Otp;
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

				return ResponseEntity.status(HttpStatus.OK)
						.body(mapper.writeValueAsString(login.getLogin(username, password)));
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
	
	@PostMapping("genrateOtp")
	public ResponseEntity<HashMap<String,Object>> genrateOtp(@RequestParam("emailid")String emailid){
		
		try{
			
			Otp otp = new Otp();
			otp.setCreatedDate(new Date());
			otp.setStatus("created");
			otp.setEmailid(emailid);
			Random random = new Random();
			
			String id = String.format("%04d", random.nextInt(10000));
			otp.setOtp(id);
			
			HashMap<String, Object> response = new HashMap<>();
			response.put("otp", id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		
		
		
	} 
}


