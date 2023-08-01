package com.hrms.Hrms.Dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TokenDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4092472193794162949L;
    
	private String refreshToken;
	
}
