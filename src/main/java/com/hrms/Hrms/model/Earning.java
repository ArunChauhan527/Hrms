package com.hrms.Hrms.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="earning")
@Data
public class Earning {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String industry;
	private String empCode;
	private Double   basic;
	private Double   hra;
	@Column(name="conveyance_allowance")
	private Double conveyanceAllowance;
	@Column(name="night_shift_allowance")
	private Double nightShiftAllowance;
	@Column(name="medical_reimbursement")
	private Double medicalReimbursement;
	@Column(name="telephone_reimbursement")
	private Double telephoneReimbursement;
	@Column(name="uniform_allowance")
	private Double uniformAllowance;
	@Column(name="meal_coupons")
	private Double mealCoupons;
	private Double lta;
	@Column(name="children_edu_allowance")
	private Double childrenEduAllowance;
	@Column(name="car_running_maintenance")
	private Double carRunningMaintenance;
	@Column(name="special_allowance")
	private Double specialAllowance;
	private Double arrears;
	@CreationTimestamp
	private Date createdDate;
	@UpdateTimestamp
	private Date updatedDate;
	private String updatedBy;
}
