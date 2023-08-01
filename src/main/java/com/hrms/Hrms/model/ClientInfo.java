package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hrms.Hrms.Enum.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientInfo")
@Data@NoArgsConstructor@AllArgsConstructor
public class ClientInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int clientId;
	@Column(unique = true)
	private String industry;
	private String company;
	private String shortForm;
	@CreationTimestamp
	private Date  createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	@Enumerated(EnumType.STRING)
	private Status status;
	

}
