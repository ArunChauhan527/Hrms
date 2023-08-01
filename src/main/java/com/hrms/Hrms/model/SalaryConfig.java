package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import lombok.Data;

@Entity
@Table(name="salary_config")
@Data
public class SalaryConfig {

	@Id
	@Column(name="config_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer configId;
	private String industry;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	@CreatedBy
	private String createdBy;
	private String updatedBy;
	private Double hra;
	private Double lta;
	private Double ca;
	private Double medRem;
	private Double pf;
	private Double epf;
	
	
	
}
