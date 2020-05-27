package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
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
	float paid_leave;
	float casual_leave; 
	String casual_carry_fd;
	String paid_carry_fd;
	String industry;
	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccess_module() {
		return access_module;
	}
	public void setAccess_module(String access_module) {
		this.access_module = access_module;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public Date getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public float getPaid_leave() {
		return paid_leave;
	}
	public void setPaid_leave(float paid_leave) {
		this.paid_leave = paid_leave;
	}
	public float getCasual_leave() {
		return casual_leave;
	}
	public void setCasual_leave(float casual_leave) {
		this.casual_leave = casual_leave;
	}
	public String getCasual_carry_fd() {
		return casual_carry_fd;
	}
	public void setCasual_carry_fd(String casual_carry_fd) {
		this.casual_carry_fd = casual_carry_fd;
	}
	public String getPaid_carry_fd() {
		return paid_carry_fd;
	}
	public void setPaid_carry_fd(String paid_carry_fd) {
		this.paid_carry_fd = paid_carry_fd;
	}
	
	
	
}
