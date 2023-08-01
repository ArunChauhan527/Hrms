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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="admin_role")
@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int roleId;
	private String roleName;
	@Column(name = "access_module")
	private String accessModule;
	private boolean accessApproval;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="updated_by")
	private String updatedBy;
	@CreationTimestamp
	private Date createdat;
	@UpdateTimestamp
	private Date updatedat;
	private String industry;
	private Boolean defaultRole;
	
}
