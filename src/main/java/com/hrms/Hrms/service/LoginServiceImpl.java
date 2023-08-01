package com.hrms.Hrms.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.directory.model.UserDoesNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.Hrms.Dto.EmailDto;
import com.hrms.Hrms.Dto.EmployeeName;
import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.Dto.StringConstants;
import com.hrms.Hrms.Dto.UserDto;
import com.hrms.Hrms.Enum.EmailType;
import com.hrms.Hrms.Enum.Status;
import com.hrms.Hrms.exception.InvalidEmail;
import com.hrms.Hrms.exception.InvalidOtp;
import com.hrms.Hrms.exception.OtpExpired;
import com.hrms.Hrms.exception.WrongPassword;
import com.hrms.Hrms.model.ClientInfo;
import com.hrms.Hrms.model.Otp;
import com.hrms.Hrms.model.PasswordMang;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.repository.ClientInfoRepository;
import com.hrms.Hrms.repository.LoginRepository;
import com.hrms.Hrms.repository.OtpRepository;
import com.hrms.Hrms.repository.PasswordMangRepository;

import lombok.extern.slf4j.Slf4j;

@Service(value="login")
@Slf4j
@Transactional
public class LoginServiceImpl  implements LoginService, UserDetailsService{

	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private OtpRepository otprepository;
	@Autowired
	private AdminService adminService;
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClientInfoRepository clientRepo;
	
	@Autowired
	private PasswordMangRepository passwordRep;
	
	
	@Override
	public String getRegister(Registration reg) {
		
		try {
			String password = genrateDefaultPassword();
		    PasswordMang pass = new PasswordMang();
		    pass.setPassword(new BCryptPasswordEncoder().encode(password));
			int roleId =  reg.getRoleId()==0? adminService.findByIndustryAndRoleName(reg.getIndustry(), StringConstants.hrRole).getRoleId():reg.getRoleId() ==2? adminService.findDefaultRole().getRoleId(): reg.getRoleId();
			reg.setRoleId(roleId);
			reg.setUserName(genrateUniqueUserName(reg.getFirstName(), reg.getLastName(), reg.getIndustry()));
			ClientInfo client = clientRepo.findByIndustry(reg.getIndustry());
			reg.setEmpCode(client.getShortForm()+String.format("%04d", new Random().nextInt(10000)));
			pass.setEmpCode(reg.getEmpCode());
			pass.setStatus(Status.Active);
			reg.setTmpPass(true);
			reg.setStatus(Status.Active);
			loginRepository.save(reg);
			passwordRep.save(pass);
			emailService.sendSimpleMessage(reg.getOfficalEmailId(), "Welcome aboard!! ",  emailService.getMessageFormat(password, EmailType.NewCandiate).replace("{candidate}", reg.getFirstName()));
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Registration users  = loginRepository.findByOfficalEmailId(username);
		
		if(users!=null && users.getStatus().equals(Status.Active))
		{
			log.info("user is avialable {}", users.getUserName());
			PasswordMang pass = passwordRep.findByEmpCode(users.getEmpCode());
			List<GrantedAuthority> list = new ArrayList<>();
			JwtDetials jwt = new JwtDetials();
			jwt.setIndustry(users.getIndustry());
			jwt.setUserName(users.getUserName());
			jwt.setEmpCode(users.getEmpCode());
			SimpleGrantedAuthority simple;
			try {
				simple = new SimpleGrantedAuthority(new ObjectMapper().writeValueAsString(jwt));
				list.add(simple);
			} catch (JsonProcessingException e) {
				
				log.error("error while setting Authority {}", e);
			}
			
			return new User(users.getOfficalEmailId(), pass.getPassword(), list);
		}
		else
		{
		 throw new UsernameNotFoundException(username +" is not present");
		}
	}
	
	@PostConstruct
	private void registerAdmin() {
		Optional<Registration> existUser = loginRepository.findById("1");
		if (existUser.isEmpty()) {
			Registration adminUser = new Registration();
			adminUser.setEmpCode("1");
			adminUser.setUserName("arun.chauhan");
			PasswordMang pass = new PasswordMang();
			pass.setEmpCode("1");
			pass.setStatus(Status.Active);
			pass.setPassword(new BCryptPasswordEncoder().encode("password"));
			adminUser.setDepartment("system");
			adminUser.setAadharcardno(9886d);
			adminUser.setAddress("ABC");
			adminUser.setCity("ABC");
			adminUser.setCreatedDate(new Date());
			adminUser.setUpdatedDate(new Date());
			adminUser.setDesignation("systemAdmin");
			adminUser.setDepartment("None");
			adminUser.setJoiningDate(new Date());
			adminUser.setOfficalEmailId("none");
			adminUser.setRoleId(1);
			adminUser.setPancard("sample");
			adminUser.setIndustry("001");
			adminUser.setState("sample");
			adminUser.setPincode(0);
			adminUser.setDOB("24-01-1994");
			adminUser.setStatus(Status.Active);
			loginRepository.saveAndFlush(adminUser);
			passwordRep.save(pass);
		}
	}

	@Override
	public UserDto getUserInfo(String userName) {
	Registration reg =	 loginRepository.findByUserNameOrOfficalEmailId(userName, userName);
	ObjectMapper mapper  = new ObjectMapper();
	UserDto user =  mapper.convertValue(reg, UserDto.class);
	return user;
	}
	
    private String genrateDefaultPassword() {
    	return RandomStringUtils.randomAlphanumeric(6);
    }

	@Override
	public void ForgetPassword(String offEmail) throws InvalidEmail {
	Registration reg = 	loginRepository.findByOfficalEmailId(offEmail);
		if(reg==null)
		{
			throw new InvalidEmail("Invalid EmailId "+ offEmail);
		}
	  Otp otp = new Otp();
	  otp.setCreated_date(new Date());
	  otp.setEmailid(offEmail);
	  otp.setStatus(Status.Active);
	  Random random  = new Random();
	  otp.setOtp(String.format("%04d", random.nextInt(10000)));
	  otprepository.save(otp);
	  try {
	  emailService.sendSimpleMessage(offEmail, "otp for forget Password", emailService.getMessageFormat(offEmail, EmailType.OtpPassword).replace("{Otp}", otp.getOtp()));
	  }catch (Exception e) {
		log.error("error while sending mail {}", e);
	}
	}
	
	@Override
	public void changePassword(String oldPassword, String newPassword, String offEmail) throws WrongPassword
	{
		Registration reg = loginRepository.findByOfficalEmailId(offEmail);
		PasswordMang pass = passwordRep.findByEmpCode(reg!=null?reg.getEmpCode():null);
		if(reg !=null && pass !=null && new BCryptPasswordEncoder().matches(oldPassword, pass.getPassword()))
		{
			if(new BCryptPasswordEncoder().matches(newPassword, pass.getPassword()))
			{
				throw new WrongPassword("Your new password cannot be same as old Password");
			}
			pass.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			reg.setTmpPass(false);
			passwordRep.save(pass);
			loginRepository.save(reg);
		}
		else
		{
			 throw new WrongPassword("Incorrect Old Password"); 
		}
		
	}
	
	
	@Override
	public void changePasswordByOtp(String otp, String password, String offEmail) throws InvalidOtp, OtpExpired, WrongPassword 
	{
		Otp otpModel = otprepository.findTop1ByOtpAndEmailid(otp, offEmail);
		if(otpModel == null)
		{
			throw new  InvalidOtp("Invalid Otp : "+ otp);
		}
		else {
			long time = (new Date().getTime() - otpModel.getCreated_date().getTime())/(60*1000);
			if(time < 15 && otpModel.getStatus().equals(Status.Active))
			{
				Registration reg = loginRepository.findByOfficalEmailId(offEmail);
				PasswordMang pass =  passwordRep.findByEmpCode(reg.getEmpCode());
				if(new BCryptPasswordEncoder().matches(password, pass.getPassword()))
				{
					throw new WrongPassword("Your new password cannot be same as old Password");
				}
				otpModel.setStatus(Status.DeActive);
				otprepository.save(otpModel);
				passwordRep.changePassword(new BCryptPasswordEncoder().encode(password), reg.getEmpCode());
			}
			else
			{
				otpModel.setStatus(Status.DeActive);
				otprepository.save(otpModel);
				throw new OtpExpired("Otp has Expired");
			}
		}
	}

	@Override
	public ResponseDataDto findByIndustry(String industry, Integer page, Integer size) {
		ResponseDataDto dto = new ResponseDataDto(); 
		Page<Registration> reg =  loginRepository.findByIndustry(industry, PageRequest.of(page, size));
		List<UserDto> users = new ObjectMapper().convertValue(reg.getContent(), new TypeReference<List<UserDto>>() {
		});
		 dto.setContent(users);
		 dto.setTotalElement(reg.getTotalElements());
		return dto;
	}
	
	@Override
	public UserDto updateStatus(Registration reg) throws UserDoesNotExistException {
	  Optional<Registration> optReg = loginRepository.findById(reg.getEmpCode());
	  if(optReg.isPresent())
	  {
		   PasswordMang pass =  passwordRep.findByEmpCode(reg.getEmpCode());
		  if(reg.getStatus().equals(Status.Active)) {
			  reg.setJoiningDate(new Date());
			  reg.setTmpPass(true);
			  reg.setUpdatedDate(new Date());
			  String password = genrateDefaultPassword(); 
			  pass.setPassword(new BCryptPasswordEncoder().encode(password));
			  passwordRep.save(pass);
			  loginRepository.save(reg);
				try {
					emailService.sendSimpleMessage(reg.getOfficalEmailId(), "Welcome aboard!! ",  emailService.getMessageFormat(password, EmailType.NewCandiate).replace("{candidate}", reg.getFirstName()));
				} catch (InvalidKeyException e) {
					log.error("InvalidKeyException {}", e);
				} catch (NoSuchAlgorithmException e) {
					log.error("NoSuchAlgorithmException {}", e);
				} catch (NoSuchPaddingException e) {
					log.error("NoSuchPaddingException {}",e );
				} catch (InvalidAlgorithmParameterException e) {
					log.error("InvalidAlgorithmParameterException {}",e);
				} catch (IllegalBlockSizeException e) {
					log.error("Illegal BlockSizeException {}", e);
				} catch (BadPaddingException e) {
					log.error("BadPadding {}",e);
				}
		  }
		  else
		  {
			  pass.setStatus(Status.DeActive);
			  reg.setUpdatedDate(new Date());
			  loginRepository.save(reg);
			  passwordRep.save(pass);
		  }
	  }
	  else{
		  throw new UserDoesNotExistException("User Doesn't exist");
	  }
	  UserDto user =new ObjectMapper().convertValue(reg, UserDto.class);
	return user;
	}

	private String genrateUniqueUserName(String firstName, String lastName, String industry)
	{
		String userName = firstName+"."+lastName;
		if(loginRepository.findByUserNameAndIndustry(userName, industry)!=null)
		{
			userName += genrateRandomNumber(2); 
		}
		return userName;
	}
	
	private String genrateRandomNumber(Integer num) {
    	return RandomStringUtils.randomNumeric(num);
    }

	@Override
	public ResponseDataDto searchEmp(String email, String industry, String department, String joiningDate, Integer page,
			Integer size) {
		
		return null;
	}

	@Override
	public EmailDto searchByIndustry(String industry) {
		EmailDto email = new EmailDto();
		List<Registration> reg =  loginRepository.findByIndustry(industry);
		email.setEmail(reg.stream().map(Registration :: getOfficalEmailId).collect(Collectors.toList()));
		email.setDepartment(reg.stream().map(Registration::getDepartment).distinct().collect(Collectors.toList()));
		return email;
	}

	@Override
	public UserDto getProfile(String id) {
		
		return null;
	}

	@Override
	public List<EmployeeName> getEmpInfo(String name, String industry) {
		return loginRepository.findByUserNameContainsAndIndustry(name,industry);
	}
}
