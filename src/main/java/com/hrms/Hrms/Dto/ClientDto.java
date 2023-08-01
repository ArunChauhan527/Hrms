package com.hrms.Hrms.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClientDto {

	
	private int clientId;
	private String industry;
	private String company;
	private String shortForm;
	private Date  createdAt;
	private String hrAdmin;
	private Date createAt;
}
