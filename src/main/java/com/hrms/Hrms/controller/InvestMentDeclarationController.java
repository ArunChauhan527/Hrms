package com.hrms.Hrms.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.model.InvestmentDeclaration;
import com.hrms.Hrms.service.InvestmentDeclarationService;

@RestController
public class InvestMentDeclarationController {

	@Autowired
	private InvestmentDeclarationService investmentService;
	
	@Autowired
	private JwtDetailsService jwt;
	
	@GetMapping("/getDeclarationByEmpCode")
	public ResponseEntity<InvestmentDeclaration> getDeclaration(){
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String empCode = smpleInd.getEmpCode();
		
		return ResponseEntity.status(HttpStatus.OK).body(investmentService.searchByEmpCodeAndSubmittedDate(empCode, new Date(), new Date()));
	}
	
	@GetMapping("/getDeclarationByIndustry")
	public ResponseEntity<Page<InvestmentDeclaration>> getDeclarationByIndustry(@RequestParam("page")int page, @RequestParam("size")int size){
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal = new GregorianCalendar(year, 04, 01);
		Date startDate = new Date(cal.getTimeInMillis());
		cal = new GregorianCalendar(year+1, 03, 31);
		Date endDate = new Date(cal.getTimeInMillis());
		return ResponseEntity.status(HttpStatus.OK).body(investmentService.searchByIndustryAndSubmittedDate(industry, startDate, endDate, page, size));
	}
	
	@PostMapping("/saveAndUpdate")
	public ResponseEntity<InvestmentDeclaration> saveAndUpdate(@RequestBody InvestmentDeclaration investmentDeclaration){
		JwtDetials smpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		String empCode  = smpleInd.getEmpCode();
		investmentDeclaration.setIndustry(industry);
		investmentDeclaration.setEmpCode(empCode);
		investmentDeclaration.setUpdatedBy(smpleInd.getUserName());
		return ResponseEntity.status(HttpStatus.OK).body(investmentService.saveInvestmentDeclaration(investmentDeclaration));
	}
	
	
}
