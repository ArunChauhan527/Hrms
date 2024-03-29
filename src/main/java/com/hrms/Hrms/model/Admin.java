package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="admin")
@Data
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int sno;
	String role;
	String access_module;
	String created_by;
	String updated_by;
	Date createdat;
	Date updatedat;
	String company;
	String industry;
	
}
