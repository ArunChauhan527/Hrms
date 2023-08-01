package com.hrms.Hrms.Dto;

import com.hrms.Hrms.model.Deductions;
import com.hrms.Hrms.model.Earning;

import lombok.Data;

@Data
public class Salary {

	private Earning earning;
	private Deductions deduction;
}
