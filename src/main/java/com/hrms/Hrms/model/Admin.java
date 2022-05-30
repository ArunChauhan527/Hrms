package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="admin")
@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sno;
	private String role;
	private String access_module;
	private String accessApproval;
	private  String created_by;
	private String updated_by;
	private Date createdat;
	private Date updatedat;
	private String company;
	private String industry;
	
}
