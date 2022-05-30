package com.hrms.Hrms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.Hrms.Dto.LeaveCount;
import com.hrms.Hrms.Enum.LeaveStatus;
import com.hrms.Hrms.Enum.LeaveType;
import com.hrms.Hrms.model.Leave;
import com.hrms.Hrms.model.LeavePolicy;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.repository.LeavePolicyRepository;
import com.hrms.Hrms.repository.LeaveRepository;
import com.hrms.Hrms.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Component("leaves")@Slf4j
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeavePolicyRepository leavePolicyRep;
	@Autowired
	private LeaveRepository leave;
	@Autowired
	private LoginRepository loginRepository;
	


	@SuppressWarnings("unused")
	@Override
	public LeaveCount leavesCount(Integer empId, String industry) {
		LeaveCount leaveCount = new LeaveCount();
		List<LeavePolicy> leavePolicy=  leavePolicyRep.findByIndustry(industry);
		List<Leave> leaves =  leave.findByEmpCodeAndCompCode(empId,industry);
		Optional<Registration> registration = loginRepository.findById(empId);
		if(leavePolicy !=null && leavePolicy.size()!=0)
		 {
				LocalDateTime currentDate = LocalDateTime.now();
				int month  = currentDate.getMonth().getValue();
				int year   = currentDate.getYear();
				float planPerMonth = leavePolicy.get(0).getPaid_leave()/12;
				float casPerMonth  = leavePolicy.get(0).getCasual_leave()/12;
				log.info(planPerMonth  +" : "+ casPerMonth);
				if(registration.isPresent())
				{
					LocalDateTime joiningDate = registration.get().getJoiningDate();
					int joiningYear = joiningDate.getYear();
					int years = joiningYear - year;
					leaveCount.setCasualLeave((month-joiningDate.getMonth().getValue())*casPerMonth+casPerMonth-leave.countByEmpCodeAndCompCodeAndType(empId, industry, LeaveType.casual));
					leaveCount.setApprovedLeave(leave.countByEmpCodeAndCompCodeAndStatus(empId, industry, LeaveStatus.approved));
					leaveCount.setUnapprovedLeave(leave.countByEmpCodeAndCompCodeAndStatus(empId, industry, LeaveStatus.unapproved));
					leaveCount.setAppliedLeave(leave.countByEmpCodeAndCompCodeAndStatus(empId, industry, LeaveStatus.applied));
					if(years==0)
					{
						leaveCount.setPlannedLeave((month-joiningDate.getMonth().getValue())*planPerMonth+planPerMonth-leave.countByEmpCodeAndCompCodeAndType(empId, industry, LeaveType.planned));
					}
					else
					{
						//need to add carryforward leaves as well
						leaveCount.setPlannedLeave((month-joiningDate.getMonth().getValue())*planPerMonth+planPerMonth);
					}
				}
		}
	return leaveCount;
	}



	@Override
	public List<Leave> findByEmpCodeAndIndustry(Integer empCode, String industry) {
		return leave.findByEmpCodeAndCompCode(empCode, industry);
	}

	@Override
	public List<Leave> saveAll(List<Leave> model) {
		return leave.saveAll(model);
	}

	@Override
	public Leave save(Leave model) {
		return save(model);
	}


}
