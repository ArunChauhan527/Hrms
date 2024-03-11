package com.hrms.Hrms.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hrms.Hrms.Dto.ChangePassword;
import com.hrms.Hrms.Dto.EmailDto;
import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.Dto.JwtRequest;
import com.hrms.Hrms.Dto.JwtResponse;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.Dto.ResponseDto;
import com.hrms.Hrms.Dto.TokenDto;
import com.hrms.Hrms.Dto.UserDto;
import com.hrms.Hrms.config.JwtAuthenticationEntryPoint;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.config.JwtTokenUtil;
import com.hrms.Hrms.exception.CannotRefreshToken;
import com.hrms.Hrms.exception.InvalidEmail;
import com.hrms.Hrms.exception.InvalidOtp;
import com.hrms.Hrms.exception.OtpExpired;
import com.hrms.Hrms.exception.WrongPassword;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.service.ExcelReader;
import com.hrms.Hrms.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {

	@Autowired
	@Qualifier(value = "login")
	private LoginService login;
	
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuth;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Autowired
	private ExcelReader excelService;
	
	@Autowired
	private JwtDetailsService jwt;


	
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
	
	
	
	@PostMapping("api/auth/getToken")
	public ResponseEntity<JwtResponse> getLogin(@RequestBody JwtRequest authenticationRequest) throws Exception {
	     log.info("information of login");
		jwtAuth.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.genrateRefreshToken(userDetails); 
		return ResponseEntity.ok(new JwtResponse(token, refreshToken));
	}
	
	
	@GetMapping("/getUserInfo")
	public ResponseEntity<UserDto> getUser(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName =  user.getUsername();	
		return  ResponseEntity.status(HttpStatus.OK).body(login.getUserInfo(userName));
	}
	
	
	@GetMapping("/forgetPassword")
	public void forgetPassword(@RequestParam("emailId") String offEmail) throws InvalidEmail{
		login.ForgetPassword(offEmail);
	}
	
	@PostMapping("/changePassword")
	public void changePassword(@RequestBody ChangePassword dto) throws WrongPassword, InvalidOtp, OtpExpired
	{
		if(StringUtils.isNotBlank(dto.getOldPassword()) )
		{
		login.changePassword(dto.getOldPassword(), dto.getNewPassword(), dto.getEmailId());
		}
		else if(StringUtils.isNotBlank(dto.getOtp()))
		{
		login.changePasswordByOtp(dto.getOtp(), dto.getNewPassword(), dto.getEmailId());
		}
	}
	
	
	@GetMapping("/getUsers")
	public ResponseEntity<ResponseDataDto> getUsers(@RequestParam("page")Integer page, @RequestParam("size")Integer size){
		SimpleGrantedAuthority smpleInd = (SimpleGrantedAuthority) SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next();
		String industry = smpleInd.getAuthority();
		return  ResponseEntity.status(HttpStatus.OK).body(login.findByIndustry(industry, page, size));
	}
	
	
	@PostMapping("/updateStatus")
	public ResponseEntity<UserDto> updateStatus(@RequestBody Registration reg)
	{
		return ResponseEntity.status(HttpStatus.OK).body(login.updateStatus(reg));
	}
	
	
	@PostMapping("/bulkRegister")
	public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) throws IllegalStateException, InvalidFormatException, IOException{
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.status(HttpStatus.OK).body(excelService.excelReader(file, industry));
	}
	
	@PostMapping("api/auth/refreshToken")
	public ResponseEntity<JwtResponse> refreshToken(@RequestBody TokenDto token) throws CannotRefreshToken
	{
		String token1  = jwtTokenUtil.refreshedToken(token.getRefreshToken());
		
		return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token1, token.getRefreshToken()));
	}
	
	@GetMapping("/searchEmp")
	public ResponseEntity<ResponseDataDto> searchEmp(@RequestParam("email")String email, @RequestParam("department")String depratment, @RequestParam("joiningDate")String joinDate, @RequestParam("page") Integer page, @RequestParam("size")Integer size)
	{
		return ResponseEntity.status(HttpStatus.OK).body(login.searchEmp(email, email, depratment, joinDate, page, size));
	}
	
	
	@GetMapping("findByIndustry")
	public ResponseEntity<EmailDto> searchByIndustry()
	{
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
	   return ResponseEntity.status(HttpStatus.OK).body(login.searchByIndustry(industry));	
	}
	
	@GetMapping("profile/{id}")
	public ResponseEntity<UserDto> getProfile(@RequestParam("id") String id){
		
		return null;
	}
	
	
	/**endpoint to return empName and EmpId 
	 * @param name
	 * @return
	 */
	@GetMapping("getEmpBy")
	public ResponseEntity<?> getEmpByNameORId(@RequestParam String name){
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.status(HttpStatus.OK).body(login.getEmpInfo(name, industry));
	}
}


