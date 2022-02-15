package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table( name = "leavePloicy")
@Data
public class LeavePolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int sno;
	String created_by;
	String updated_by;
	Date createdat;
	Date updatedat;
	String company;
	float paid_leave;
	float casual_leave; 
	String casual_carry_fd;
	String paid_carry_fd;
	String industry;
	
}
