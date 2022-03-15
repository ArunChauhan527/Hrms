package com.hrms.Hrms.controller;

import java.util.HashMap;
import java.util.List;

import com.hrms.Hrms.Dto.LeaveCount;
import com.hrms.Hrms.model.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.service.LeaveService;

@RestController
public class LeavesController {

	@Autowired
	@Qualifier("leaves")
	LeaveService leave;


	@PostMapping("leaves")
	public ResponseEntity<List<Leave>> leaves(@RequestParam("empId") Integer empId, @RequestParam("industry")String industry){
       return ResponseEntity.ok(leave.findByEmpCodeAndIndustry(empId, industry));
	}

	@GetMapping("leavesCount")
	public ResponseEntity<LeaveCount> leavesCount(@RequestParam("empId") Integer empId, @RequestParam("industry")String industry){
		return ResponseEntity.ok(leave.leavesCount(empId,industry));
	}

	@PostMapping("saveLeave")
	public ResponseEntity<Leave> saveLeave(@RequestBody Leave model){
		return ResponseEntity.status(HttpStatus.CREATED).body(leave.save(model));
	}
	
}
