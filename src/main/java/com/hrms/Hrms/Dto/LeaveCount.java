package com.hrms.Hrms.Dto;

import lombok.Data;

@Data
public class LeaveCount {

    private double plannedLeave;
    private double casualLeave;
    private double approvedLeave;
    private double unapprovedLeave;
    private double appliedLeave;


}
