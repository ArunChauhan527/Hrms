package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="otp")
@Data@NoArgsConstructor@AllArgsConstructor
public class Otp {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int sno;
	
	String otp;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	Date created_date;
	String status;
	String emailid;
	
	
}
