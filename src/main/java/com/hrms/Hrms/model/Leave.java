package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.*;

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
	Integer sno;
    @Column(name = "emp_code")	
	Integer empCode;
    @Enumerated(EnumType.STRING)
	LeaveType type;
    @Enumerated(EnumType.STRING)
    LeaveDuration leaveDuration;
    @Enumerated(EnumType.STRING)
	LeaveStatus status;
    Double noLeave;
    Date appliedon;
    Date fromDate;
    Date toDate;
    String applied_by;
    Date approved_on;
    String approved_by;
    String updated_by;
    Date updated_on;
    String industry;
    String reason;
    @Column(name = "comp_code")
    String compCode;
	
}
