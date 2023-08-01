package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="investment_declaration")
@Data
public class InvestmentDeclaration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uuid;
	private Integer ppf;
	private Integer houseRent;
	private Integer eduInterest;
	private Integer specialDonation;
	private Integer medicalInsurance;
	private Integer lta;
	private Integer annuityPlan;
	private Integer lifeInsurance;
	private Integer nsc;
	private Integer ssy;
	private Integer stampDregCharge;
	private Integer scss;
	private Integer tuitionFee;
	private Integer hlpr;
	private Integer elss;
	private Integer nps;
	private Integer homeLoan;
	private String industry;
	@Column(name = "emp_code")
	private String empCode;
	private Date submittedDate;
	private Date updatedDate;
	private String updatedBy;
	
}
