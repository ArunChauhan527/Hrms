package com.hrms.Hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hrms.Hrms.Enum.Status;

import lombok.Data;

@Entity
@Table(name = "password_hrms")
@Data
public class PasswordMang {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Sno;
	private String password;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "emp_code")
	private String empCode;
}
