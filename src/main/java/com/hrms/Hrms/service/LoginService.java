package com.hrms.Hrms.service;

import java.util.List;

import com.amazonaws.services.directory.model.UserDoesNotExistException;
import com.hrms.Hrms.Dto.EmailDto;
import com.hrms.Hrms.Dto.EmployeeName;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.Dto.UserDto;
import com.hrms.Hrms.exception.InvalidEmail;
import com.hrms.Hrms.exception.InvalidOtp;
import com.hrms.Hrms.exception.OtpExpired;
import com.hrms.Hrms.exception.WrongPassword;
import com.hrms.Hrms.model.Otp;
import com.hrms.Hrms.model.Registration;

public interface LoginService {
	
	/**
	 * @param reg
	 * @return
	 */
	String getRegister(Registration reg);
	
	/**
	 * @param otp
	 * @return
	 */
	String genrateOtp(Otp otp);
	

	/**
	 * @param offEmail
	 * @throws InvalidEmail
	 */
	void ForgetPassword(String offEmail)throws InvalidEmail;
	
	/**
	 * @param userName
	 * @return
	 */
	UserDto getUserInfo(String userName);

	/**
	 * @param otp
	 * @param password
	 * @param offEmail
	 * @throws InvalidOtp
	 * @throws OtpExpired
	 * @throws WrongPassword 
	 */
	void changePasswordByOtp(String otp, String password, String offEmail) throws InvalidOtp, OtpExpired, WrongPassword;

	/**
	 * @param oldPassword
	 * @param newPassword
	 * @param offEmail
	 * @throws WrongPassword
	 */
	void changePassword(String oldPassword, String newPassword, String offEmail) throws WrongPassword;
	
	/**
	 * @param industry
	 * @param page
	 * @param size
	 * @return
	 */
	ResponseDataDto findByIndustry(String industry, Integer page, Integer size);

	/**
	 * @param reg
	 * @return
	 * @throws UserDoesNotExistException
	 */
	UserDto updateStatus(Registration reg) throws UserDoesNotExistException;
	
	/**
	 * @param email
	 * @param industry
	 * @param department
	 * @param joiningDate
	 * @param page
	 * @param size
	 * @return
	 */
	ResponseDataDto searchEmp(String email,  String industry, String department, String
			joiningDate, Integer page, Integer size ); 
	

	EmailDto searchByIndustry(String industry);
	
	/**
	 * @param id
	 * @return
	 */
	UserDto getProfile(String id);
	
	
	/**
	 * @param name
	 * @param industry
	 * @return
	 */
	List<EmployeeName> getEmpInfo(String name, String industry);
}
