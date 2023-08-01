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
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.model.SalaryConfig;
import com.hrms.Hrms.service.SalaryConfigService;

@RestController
public class SalaryConfigController {
	
	@Autowired
	private SalaryConfigService service;
	
	@Autowired
	private JwtDetailsService jwt;
	
	
	@GetMapping("getSalaryConfig")
	public ResponseEntity<SalaryConfig> getSalaryConfig(){
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return ResponseEntity.status(HttpStatus.OK).body(service.findByIndustry(smpleInd.getIndustry()));
	}
	
	@PostMapping("getSalaryConfigs")
	public ResponseEntity<List<SalaryConfig>> getSalaryConfigs(@RequestBody List<String> industry){
		return ResponseEntity.status(HttpStatus.OK).body(service.findByIndustries(industry));
	}
	
	@PostMapping("save/salaryConfig")
	public ResponseEntity<SalaryConfig> saveSalaryConfig(@RequestBody SalaryConfig salary){
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		salary.setIndustry(smpleInd.getIndustry());
		salary.setCreatedBy(smpleInd.getEmpCode());
		salary.setUpdatedBy(smpleInd.getEmpCode());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(salary));
	}

}
