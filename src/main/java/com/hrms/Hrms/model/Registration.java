package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "regestration")
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
	
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	Date registration_date;

	public int getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(int emp_code) {
		this.emp_code = emp_code;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getPersonal_email_id() {
		return personal_email_id;
	}

	public void setPersonal_email_id(String personal_email_id) {
		this.personal_email_id = personal_email_id;
	}

	public String getOffical_email_id() {
		return offical_email_id;
	}

	public void setOffical_email_id(String offical_email_id) {
		this.offical_email_id = offical_email_id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}



	public Double getAadharcardno() {
		return aadharcardno;
	}

	public void setAadharcardno(Double aadharcardno) {
		this.aadharcardno = aadharcardno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

}
