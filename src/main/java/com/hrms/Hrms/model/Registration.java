package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import com.hrms.Hrms.Enum.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registration")
@Data@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Registration {

	@Id
	@Column(name="emp_code")
	private String empCode;
	@NonNull
	@NotBlank
	private String DOB;
	@Column(name = "personal_email_id")
	private String personalEmailId;
	@NotBlank
	@NonNull
	@Column(name = "offical_email_id", unique = true)
	private String officalEmailId;
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
	private String industry;
	@CreationTimestamp
	private Date createdDate;
	@UpdateTimestamp
	private Date updatedDate;
	private Date joiningDate;
	private String reportingManager;
	private String gender;
	private int roleId;
	private String firstName;
	private String lastName;
	private boolean tmpPass;
	@Enumerated(EnumType.STRING)
	private Status status;

}
