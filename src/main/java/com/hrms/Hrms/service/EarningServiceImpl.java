package com.hrms.Hrms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.Earning;
import com.hrms.Hrms.repository.AttendanceRepository;
import com.hrms.Hrms.repository.EarningRepository;
import com.hrms.Hrms.repository.LeaveRepository;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Service@Slf4j@Transactional
public class EarningServiceImpl  implements EarningService{

	@Autowired
	private EarningRepository earningRepo;
	
	@Autowired
	private  AttendanceRepository attendanceRepo;
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public Earning saving(Earning earning) {
		return earningRepo.save(earning);
	}

	@Override
	public List<Earning> getByEmpCode(String empCode) {
		return earningRepo.findByEmpCode(empCode);
	}

	@Override
	public Page<Earning> getByIndustry(String industry, int page, int size) {
		return earningRepo.findByIndustry(industry, PageRequest.of(page, size));
	}

	@Override
	public List<Earning> getByEmpCodeAndCreatedDate(String empCode, Date startDate, Date endDate) {
		return earningRepo.findByEmpCodeAndCreatedDateBetween(empCode, startDate, endDate);
	}

	@Override
	public Earning getEarningByIndustryandEmpCodeAndCreatedDate(String industry, String empCode, Date createdDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(createdDate);
		return earningRepo.findByIndustryAndEmpCodeAndCreatedDate(industry, empCode, c.get(Calendar.MONTH)+1);
	}

	@Override
	public void getMonthlyEarning(int Month, String empCode, String industry, int year) {
	Long attendCount = attendanceRepo.countByEmpCodeAndMonthAndIndustryAndSelf(empCode, Month, industry, year);
	Long regulateCount = attendanceRepo.countByEmpCodeAndMonthAndIndustryAndRegulate(empCode, Month, industry, year);
	Long leaveCount = leaveRepository.countByEmpCodeAndIndustryAndTypeAndStatusAndMonthFromDate(empCode, industry, Month, year);
	Long lwpCount = leaveRepository.countByEmpCodeAndIndustryAndTypeLWPAndStatusAndMonthFromDate(empCode, industry, Month, year);
	log.info("count of attendance {}, regulate {}, leaveCount {}, lwpCount {}", attendCount, regulateCount, leaveCount, lwpCount );
		
	}

}
