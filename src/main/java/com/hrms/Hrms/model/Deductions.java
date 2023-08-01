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
@Table(name="deductions")
@Data
public class Deductions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id; 
	private String industry;
	private String empCode;
	private Double tds;
	private Double pf;
	@Column(name = "meal_coupons_deductions")
	private Double mealCouponsDeductions;
	@Column(name="employer_pf")
	private Double employerPF;
	@Column(name="loan_advance")
	private Double loanAdvance;
	@Column(name="other_deduction")
	private Double otherDeduction;
	private Date createdDate;
	private Date updatedDate;
	private String updatedBy;
	
}
