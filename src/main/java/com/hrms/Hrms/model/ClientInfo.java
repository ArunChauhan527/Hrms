package com.hrms.Hrms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientInfo")
@Data@NoArgsConstructor@AllArgsConstructor
public class ClientInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String sno;
	private String industry;
	private String company;
	private String shortForm;
	

}
