package com.hrms.Hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.Dto.LeaveCount;
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
	
	@GetMapping("getNationalHoliday")
	public ResponseEntity<List<NationalHoliday>> getNationalHoliday(@RequestParam("industry") String industry ){
		return ResponseEntity.status(HttpStatus.OK).body(nationalService.findByIndustry(industry));
	}
}
