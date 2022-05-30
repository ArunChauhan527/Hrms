package com.hrms.Hrms.Dto;

import lombok.Data;
import lombok.ToString;

@Data@ToString
public class LeaveCount {

    private double plannedLeave;
    private double casualLeave;
    private double approvedLeave;
    private double unapprovedLeave;
    private double appliedLeave;


}
