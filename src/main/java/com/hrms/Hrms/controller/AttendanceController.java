package com.hrms.Hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hrms.Hrms.model.Attendance;
import com.hrms.Hrms.service.AttendanceService;

@RestController
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private JwtDetailsService jwt;
	
	@PostMapping("saveAttendance")
	public ResponseEntity<Attendance> saveAttendance(@RequestBody Attendance att){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		att.setIndustry(industry);
		att.setCreatedBy(smpleInd.getUserName());
		att.setUpdatedBy(smpleInd.getUserName());
		att.setEmpCode(smpleInd.getEmpCode());
		return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.save(att));
	}
	
	@GetMapping("getAttendance")
	public ResponseEntity<List<Attendance>> getAttendance(@RequestParam("startDate")String start, @RequestParam("endDate")String end) throws ParseException{
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
		LocalDateTime local = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()).toLocalDate().atTime(LocalTime.MAX);
		endDate = Date.from(local.atZone(ZoneId.systemDefault()).toInstant()); 
		return ResponseEntity.status(HttpStatus.OK).body(attendanceService.weeklyAttendnace(smpleInd.getEmpCode(), startDate, endDate, industry, smpleInd.getIndustry()));
	}
	
	@PostMapping("regulate")
	public ResponseEntity<Attendance> regulate(@RequestBody Attendance regulate){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		regulate.setIndustry(industry);
		regulate.setCreatedBy(smpleInd.getUserName());
		regulate.setUpdatedBy(smpleInd.getUserName());
		regulate.setUpdatedAt(new Date());
		regulate.setEmpCode(smpleInd.getEmpCode());
		return ResponseEntity.status(HttpStatus.OK).body(attendanceService.regulate(regulate));
	}
	
	@GetMapping("today")
	public ResponseEntity<Attendance> today(){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String empCode  = smpleInd.getEmpCode();
		return ResponseEntity.status(HttpStatus.OK).body(attendanceService.today(empCode));
	}
	
}
