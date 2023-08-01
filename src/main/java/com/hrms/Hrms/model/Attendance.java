package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.hrms.Hrms.Enum.Action;
import com.hrms.Hrms.Enum.AttandanceStatus;

import lombok.Data;

@Entity
@Table(name = "attandance_hrms")
@Data
public class Attendance {

	@Column(name = "att_id")
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "uuid2")
	@Id
	private String attId;
	@Column(name = "emp_code")
	private String empCode;
	private String industry;
	private Date punchIn;
	private Date punchOut;
	@Enumerated(EnumType.STRING)
	private AttandanceStatus status;
	@Enumerated(EnumType.STRING)
	private Action action;
	@CreationTimestamp
	private Date createdAt;
	private String createdBy;
	@UpdateTimestamp
	private Date updatedAt;
	private String updatedBy;
	private String approvedBy;
	private Date approvedAt;
	private Long value;
	@Column(columnDefinition = "text", name="reason")
	private String reason;
}
