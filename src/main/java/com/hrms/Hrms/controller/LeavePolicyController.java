package com.hrms.Hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.model.LeavePolicy;
import com.hrms.Hrms.service.LeavePolicyService;

@RestController
@RequestMapping("leavePolicy")
public class LeavePolicyController {

	@Autowired
	private LeavePolicyService leavePolicyService;
	
	
	@PostMapping("/save")
	public ResponseEntity<LeavePolicy> saveLeavePolicy(@RequestBody LeavePolicy leavePolicy){
		return ResponseEntity.status(HttpStatus.CREATED).body(leavePolicyService.savePolicy(leavePolicy));
	}
	
	@GetMapping("/findByIndustry")
	public ResponseEntity<List<LeavePolicy>> findByIndustry(@RequestParam("industry") String industry){
		return ResponseEntity.ok(leavePolicyService.findByIndustry(industry));
	}
	
}
