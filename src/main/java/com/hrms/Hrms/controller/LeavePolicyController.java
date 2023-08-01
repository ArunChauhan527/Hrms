package com.hrms.Hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.model.LeavePolicy;
import com.hrms.Hrms.model.NationalHoliday;
import com.hrms.Hrms.service.LeavePolicyService;
import com.hrms.Hrms.service.NationalHolidayService;


@RestController
@RequestMapping("leavePolicy")
public class LeavePolicyController {

	@Autowired
	private LeavePolicyService leavePolicyService;
	
	@Autowired
	private NationalHolidayService nationalHolidayService;
	
	@Autowired
	private JwtDetailsService jwt;
	
	
	@PostMapping("/save")
	public ResponseEntity<LeavePolicy> saveLeavePolicy(@RequestBody LeavePolicy leavePolicy){
		return ResponseEntity.status(HttpStatus.CREATED).body(leavePolicyService.savePolicy(leavePolicy));
	}
	
	@GetMapping("/findByIndustry")
	public ResponseEntity<LeavePolicy> findByIndustry(){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.ok(leavePolicyService.findByIndustry(industry));
	}
	
	@GetMapping("/getNationalHoliday")
	public ResponseEntity<List<NationalHoliday>> findNationalHolidayByIndustry()
	{
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.ok(nationalHolidayService.findByIndustry(industry));
	}
	
	@PostMapping("/importNationalHoliday")
	public ResponseEntity<List<NationalHoliday>> importNationalHoliday(@RequestParam("file") MultipartFile file){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.ok(nationalHolidayService.importExcel(file, industry));
	}
	
}
