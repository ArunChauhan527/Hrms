package com.hrms.Hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.LeavePolicy;
import com.hrms.Hrms.repository.LeavePolicyRepository;


@Service
public class LeavePolicyServiceImpl implements LeavePolicyService	 {

	
	@Autowired
	private LeavePolicyRepository leavePolicyRep;
	
	@Override
	public LeavePolicy savePolicy(LeavePolicy leavePolicy) {
		return leavePolicyRep.save(leavePolicy);
	}

	@Override
	public LeavePolicy findByIndustry(String industry) {
		return leavePolicyRep.findByIndustry(industry);
	}

}
