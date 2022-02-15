package com.hrms.Hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "regestration")
@Data@Getter@Setter
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int emp_code;
	@NonNull
	@NotBlank
	String DOB;
	String personal_email_id;
	@NotBlank
	@NonNull
	String offical_email_id;
	@NonNull
	@NotBlank
	String department;
	@NonNull
	@NotBlank
	String designation;
	@NonNull
	@NotBlank
	String address;
	@NonNull
	@NotBlank
	String city;
	@NonNull
	int pincode;
	@NonNull
	@NotBlank
	String state;
	@NonNull
	@NotBlank
	String pancard;
	@NonNull
    Double aadharcardno;
	@NonNull
	@NotBlank
	@Column(name = "user_name")
	String userName;
	@NonNull
	@NotBlank
	String password;
	@NonNull
	@NotBlank
	String industry;

}
