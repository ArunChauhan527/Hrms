package com.hrms.Hrms.Dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChangePassword  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4705938391645900287L;

	private String otp;
	private String oldPassword;
	private String newPassword;
	private String emailId;
	
}
