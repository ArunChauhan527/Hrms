package com.hrms.Hrms.Dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hrms.Hrms.Enum.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor@AllArgsConstructor
public class UserDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7721497587906613059L;
	
	private String emp_code;
	@NonNull
	@NotBlank
	private String DOB;
	
	private String personalEmailId;
	@NotBlank
	@NonNull
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
	private String userName;
	@NonNull
	@NotBlank
	private String industry;
	private Date createdDate;
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
