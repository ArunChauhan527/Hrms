package com.hrms.Hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "regestration")
@Data@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emp_code;
	@NonNull
	@NotBlank
	private String DOB;
	private String personal_email_id;
	@NotBlank
	@NonNull
	private String offical_email_id;
	@NonNull
	@NotBlank
	private String department;
	@NonNull
	@NotBlank
	private String designation;
	@NonNull
	@NotBlank
	private String address;
	@NonNull
	@NotBlank
	private String city;
	@NonNull
	private int pincode;
	@NonNull
	@NotBlank
	private String state;
	@NonNull
	@NotBlank
	private String pancard;
	@NonNull
    private Double aadharcardno;
	@NonNull
	@NotBlank
	@Column(name = "user_name")
	private String userName;
	@NonNull
	@NotBlank
	private String password;
	@NonNull
	@NotBlank
	private String industry;
	private Date createdDate;
	private Date updatedDate;
	private LocalDateTime joiningDate;

}
