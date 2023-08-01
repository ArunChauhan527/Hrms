package com.hrms.Hrms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.hrms.Hrms.Dto.LeaveCount;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.Enum.LeaveStatus;
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
	private LeaveRepository leaveRepo;
	
	@Autowired
	private LeavePolicyRepository leavePolicyRepo;
	
	@Autowired
	private LoginRepository loginRepo;
	



	@Override
	public LeaveCount leavesCount(String empId, String industry) {
		LeaveCount leaveCount = new LeaveCount();
		LeavePolicy leavePol =  leavePolicyRepo.findByIndustry(industry);
		Optional<Registration> reg  = loginRepo.findById(empId);
		leaveCount.setAppliedLeave(leaveRepo.countByEmpCodeAndIndustryAndStatus(empId, industry, LeaveStatus.applied));
		leaveCount.setApprovedLeave(leaveRepo.countByEmpCodeAndIndustryAndStatus(empId, industry, LeaveStatus.approved));
		leaveCount.setUnapprovedLeave(leaveRepo.countByEmpCodeAndIndustryAndStatus(empId, industry, LeaveStatus.unapproved));
		if(reg.isPresent())
		{
			Registration regis = reg.get();
			Date joinDate =  regis.getJoiningDate();
			Calendar cal  =  Calendar.getInstance();
			int curYear   =  cal.get(Calendar.YEAR);
			int curMonth =  cal.get(Calendar.MONTH)+1;
			cal.setTime(joinDate);
			int joinMonth = cal.get(Calendar.MONTH)+1;
			int joinYear  = cal.get(Calendar.YEAR);
			log.info("joining Month of Employee : {} currentMonth is : {}", joinMonth, curMonth);
			
			float current = curMonth < joinMonth ? curMonth : curYear == joinYear? curMonth-joinMonth : curMonth;
			float perMonthCasual      = leavePol.getCasual_leave()/12;
			float PerMonthPaid    = leavePol.getPaid_leave()/12; 
			leaveCount.setCasualLeave(current*perMonthCasual);
			leaveCount.setPlannedLeave(current*PerMonthPaid);
			
		}
		
	return leaveCount;
	}



	@Override
	public ResponseDataDto findByEmpCodeAndIndustry(String empCode, String industry, Integer page, Integer size) {
		ResponseDataDto response = new ResponseDataDto();
		Page<Leave> res =  leaveRepo.findByEmpCodeAndIndustry(empCode, industry, PageRequest.of(page, size));
		response.setContent(res.getContent());
		response.setTotalElement(res.getTotalElements());
		 return response;
	}

	@Override
	public List<Leave> saveAll(List<Leave> model) {
		return leaveRepo.saveAll(model);
	}

	@Override
	public Leave save(Leave model) {
		return leaveRepo.save(model);
	}



	@Override
	public Long RequestCount(String empCode, String industry) {
		return leaveRepo.countByEmpCodeAndIndustryAndStatus(empCode, industry, LeaveStatus.applied);
	}


}
