package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Registration")
public class Registration {

	@Id
	int emp_code;
	String DOB;
	String personal_email_id;
	String Offical_email_id;
	String department;
	String designation;
	String address;
	String city;
	int pincode;
	String state;
	String pancard;
	int aadharcardno;
	String user_name;
	String password;
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
		return Offical_email_id;
	}
	public void setOffical_email_id(String offical_email_id) {
		Offical_email_id = offical_email_id;
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
	public int getAadharcardno() {
		return aadharcardno;
	}
	public void setAadharcardno(int aadharcardno) {
		this.aadharcardno = aadharcardno;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
