package com.hrms.Hrms.service;

import com.hrms.Hrms.model.LeavePolicy;

public interface LeavePolicyService {

	LeavePolicy savePolicy(LeavePolicy leavePolicy);
	
	LeavePolicy findByIndustry(String industry);
}
