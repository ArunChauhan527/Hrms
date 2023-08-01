package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import com.hrms.Hrms.Enum.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="otp")
@Data@NoArgsConstructor@AllArgsConstructor
public class Otp {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sno;
	private String otp;
	@NonNull
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String emailid;
	
	
}
