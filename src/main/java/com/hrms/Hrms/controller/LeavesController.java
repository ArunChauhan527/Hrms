package com.hrms.Hrms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.Dto.LeaveCount;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.model.Leave;
import com.hrms.Hrms.model.NationalHoliday;
import com.hrms.Hrms.service.LeaveService;
import com.hrms.Hrms.service.NationalHolidayService;

@RestController
public class LeavesController {

	@Autowired
	@Qualifier("leaves")
	private LeaveService leave;
	
	@Autowired
	private NationalHolidayService nationalService;
	
	@Autowired
	private JwtDetailsService jwt;


	@GetMapping("leaves")
	public ResponseEntity<ResponseDataDto> leaves(@RequestParam("page")int page, @RequestParam("size")int size){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		String empId = smpleInd.getEmpCode();
       return ResponseEntity.ok(leave.findByEmpCodeAndIndustry(empId, industry, page, size));
	}

	@GetMapping("leavesCount")
	public ResponseEntity<LeaveCount> leavesCount(){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.ok(leave.leavesCount(smpleInd.getEmpCode(),industry));
	}

	@PostMapping("saveLeave")
	public ResponseEntity<Leave> saveLeave(@RequestBody Leave model){
		JwtDetials jwtDetials = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		model.setAppliedBy(jwtDetials.getUserName());
		model.setIndustry(jwtDetials.getIndustry());
		model.setUpdatedBy(jwtDetials.getUserName());
		model.setEmpCode(jwtDetials.getEmpCode());
		model.setAppliedon(new Date());
		model.setUpdatedOn(new Date());
		return ResponseEntity.status(HttpStatus.CREATED).body(leave.save(model));
	}
	
	@GetMapping("getNationalHoliday")
	public ResponseEntity<List<NationalHoliday>> getNationalHoliday(){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.status(HttpStatus.OK).body(nationalService.findByIndustry(industry));
	}
	
	@GetMapping("requestCount")
	public ResponseEntity<Long> getRequestCount(){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return ResponseEntity.status(HttpStatus.OK).body(leave.RequestCount(smpleInd.getEmpCode(), smpleInd.getIndustry()));
	}
	
}
