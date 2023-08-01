package com.hrms.Hrms.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.Dto.Salary;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.model.Deductions;
import com.hrms.Hrms.model.Earning;
import com.hrms.Hrms.service.DeductionService;
import com.hrms.Hrms.service.EarningService;

@RestController

public class PayrollController {
	
	@Autowired
	private EarningService earningService;
	
	@Autowired
	private DeductionService deductionService;
	
	@Autowired
	private JwtDetailsService jwt;
	
	
	@PostMapping("salary/save&update")
	public ResponseEntity<Salary> saveUpdate(@RequestBody Salary salary){
		
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String empCode = smpleInd.getEmpCode();
		
		Earning earn = salary.getEarning();
		earn.setEmpCode(empCode);
		earn.setIndustry(smpleInd.getIndustry());
		salary.setEarning(earningService.saving(earn));
		
		Deductions deduction = salary.getDeduction();
		deduction.setEmpCode(empCode);
		deduction.setIndustry(smpleInd.getIndustry());
	    salary.setDeduction(deductionService.save(deduction)); 
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(salary);
	}
	
	@GetMapping("getEarning/{empId}")
	public ResponseEntity<Earning> getEarning(@PathVariable String empId){
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return ResponseEntity.status(HttpStatus.OK).body(earningService.getEarningByIndustryandEmpCodeAndCreatedDate(smpleInd.getIndustry(), empId, new Date()));
	}
	
	@GetMapping("monthlySalary/{empId}/{month}")
	public ResponseEntity<?> getMonthlySalary(@PathVariable String empId, @PathVariable Integer month)
	{
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		earningService.getMonthlyEarning(month, empId, smpleInd.getIndustry(), cal.get(Calendar.YEAR));
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
