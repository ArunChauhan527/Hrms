package com.hrms.Hrms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.service.LeaveService;

@RestController
public class LeavesControllerr {

	@Autowired
	@Qualifier("leaves")
	LeaveService leave;
	
	@PostMapping("adminLeave")
	public ResponseEntity<?> adminLeaves(@RequestParam("company") String company)
	{
		try{
			HashMap<String, Object> response = new HashMap<>();
					
			
			if(company==null)company="";
			
			if(!company.isEmpty())
			{
				Admin adminpolicy = leave.leaveploicy(company);
				
				if(adminpolicy==null)
				{
					response.put("message", "Some error occurs or there is no such company");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
				else
				{
					
					return ResponseEntity.status(HttpStatus.OK).body(adminpolicy);
					
				}
				
			}
			else
			{
				response.put("message", "company can't be empty");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> response = new HashMap<>();
			response.put("message", "some error occurs");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		
		
	}
	
	@PostMapping("leavesCount")
	public ResponseEntity<?> leaveCount(@RequestParam("empId")Integer empId,@RequestParam("industry")String industry)
	{
		try{
			HashMap<Object, Object> response1 = new HashMap<>();
			if(empId==null)empId=0;
			if(industry==null)industry="";
			if(empId!=0 && industry.isEmpty())
			{
				response1.put("message", "empId or industry field is empty");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response1);
			}
			else
			{
			response1 = leave.levesCount(empId, industry);
			if(response1==null)
			{
				HashMap<String, Object> response12 = new HashMap<>();
				response12.put("leaves", 0);
				return ResponseEntity.status(HttpStatus.OK).body(response12);
			}
			else if(response1.size()==0)
			{
				HashMap<String, Object> response12 = new HashMap<>();
				response12.put("leaves", 0);
				return ResponseEntity.status(HttpStatus.OK).body(response12);
			}
		   else if(response1.containsKey("message"))
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response1);
			}
			else
			{
				return ResponseEntity.status(HttpStatus.OK).body(response1);
			}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			HashMap<String, Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		
	}
	
}
