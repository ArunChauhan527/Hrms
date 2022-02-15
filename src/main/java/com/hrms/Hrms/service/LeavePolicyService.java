package com.hrms.Hrms.service;

import java.util.List;

import com.hrms.Hrms.model.LeavePolicy;

public interface LeavePolicyService {

	LeavePolicy savePolicy(LeavePolicy leavePolicy);
	
	List<LeavePolicy> findByIndustry(String industry);
}
