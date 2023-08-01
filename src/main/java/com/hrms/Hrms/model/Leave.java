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

import com.hrms.Hrms.Enum.Action;
import com.hrms.Hrms.Enum.LeaveDuration;
import com.hrms.Hrms.Enum.LeaveStatus;
import com.hrms.Hrms.Enum.LeaveType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="leaves")
@Data@NoArgsConstructor@AllArgsConstructor
public class Leave {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sno;
    @Column(name = "emp_code")	
	private String empCode;
    @Enumerated(EnumType.STRING)
	private LeaveType type;
    @Enumerated(EnumType.STRING)
    private LeaveDuration leaveDuration;
    @Enumerated(EnumType.STRING)
	private LeaveStatus status;
    @Enumerated(EnumType.STRING)
	private Action action;
    private Double noLeave;
    private Date appliedon;
    private Date fromDate;
    private Date toDate;
    @Column(name = "applied_by")
    private String appliedBy;
    @Column(name = "approved_on")
    private Date approvedOn;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_on")
    private Date updatedOn;
    private String industry;
    private String reason;
	
}
