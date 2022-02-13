package com.hrms.Hrms.controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.Hrms.Dto.JwtRequest;
import com.hrms.Hrms.Dto.JwtResponse;
import com.hrms.Hrms.Dto.ResponseDto;
import com.hrms.Hrms.config.JwtAuthenticationEntryPoint;
import com.hrms.Hrms.config.JwtTokenUtil;
import com.hrms.Hrms.model.Otp;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.service.EmailServiceImpl;
import com.hrms.Hrms.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	@Qualifier(value = "login")
	private LoginService login;
	
	@Autowired
	private EmailServiceImpl email;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuth;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;


	@PostMapping(value = "loginDB")
	public ResponseEntity<?> getlogin(@RequestBody JwtRequest Data) throws JsonProcessingException {
		try {
			ResponseDto response = new ResponseDto();

			String username = Data.getUsername();
			if (username == null)
				username = "";
			String password = Data.getPassword();
			if (password == null)
				password = "";

			if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
				response.setMessage("Username and password can't be empty");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			} else {

				Registration result = login.getLogin(username, password);
				if(result==null)
				{
					response.setMessage("Username and password is incorrect");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
				else
				{
					return ResponseEntity.status(HttpStatus.OK)
							.body(result);	
				}
				
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
	public ResponseEntity<ResponseDto> resgister(@RequestBody Registration data) throws JsonProcessingException {
		ResponseDto regis = new ResponseDto();

		try {
			
			String msg = login.getRegister(data);
			
			if(msg.equalsIgnoreCase("success"))
			{
				regis.setMessage("Saved successfully");
				return ResponseEntity.status(HttpStatus.OK).body(regis);
			}
			else
			{
				
				regis.setMessage(msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(regis);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			regis.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(regis);
		}
		
	}
	
	@PostMapping("genrateOtp")
	public ResponseEntity<HashMap<String,Object>> genrateOtp(@RequestParam("emailid")String emailid){
		
		try{
			if(!emailid.isEmpty()){
			Otp otp = new Otp();
			otp.setStatus("created");
			otp.setEmailid(emailid);
			Random random = new Random();
			
			String id = String.format("%04d", random.nextInt(10000));
			otp.setOtp(id);
			
			
			HashMap<String, Object> response = new HashMap<>();
			String result = login.genrateOtp(otp);
			if(result.equalsIgnoreCase("success"))
			{
			response.put("otp", otp);
			
			email.sendSimpleMessage(emailid, "Otp to reset password", "Hi, \n \n\n Please find the otp to reset password. \r \n Otp: "+id+". \n Note this otp will be active only for 15 mins.  \n Thanks");
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			else
			{
				response.put("message", "some error occurs in creating otp");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			}
			else
			{
				HashMap<String, Object> response = new HashMap<>();
				response.put("message", "Please provide emailid");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		
		
		
	} 
	
	@SuppressWarnings("unchecked")
	@PostMapping("otpChangePassword")
	public ResponseEntity<HashMap<String,Object>> otpChangePassword(@RequestBody String req){
		
		try{
			String response="";
			if(req==null)
			{
				req="";
			}
			else if(!req.isEmpty())
			{
				HashMap<String, Object> map = new ObjectMapper().readValue(req, HashMap.class);
				response = login.changePassword(map);
			}
			HashMap<String, Object> response1 = new HashMap<>();
			response1.put("message", response);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response1);
			
		}catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		
		
		
	} 
	
	
	@PostMapping("/getToken")
	public ResponseEntity<JwtResponse> getLogin(@RequestBody JwtRequest authenticationRequest) throws Exception {
	
		jwtAuth.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	
	
}


