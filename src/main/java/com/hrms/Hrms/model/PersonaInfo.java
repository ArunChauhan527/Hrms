package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "personal_info")
public class PersonaInfo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Sno;
	private String passportNo;
	private String maritalStatus;
	private String emp_code;
    @CreationTimestamp
	private Date createdAt;
    @UpdateTimestamp
	private Date updateAt;
    private String updatedBy;
}
