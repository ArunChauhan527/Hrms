package com.hrms.Hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.Dto.ResponseDto;
import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.model.ClientInfo;
import com.hrms.Hrms.model.MenuItem;
import com.hrms.Hrms.service.AdminService;

@RestController
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	
	
	
	
	@PostMapping("save")
	public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin){
		return ResponseEntity.status(HttpStatus.CREATED).body(adminService.save(admin));
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDto> deleteById(@PathVariable int id)
	{
		adminService.delete(id);
		ResponseDto response = new ResponseDto();
		response.setMessage("Deleted Successfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("findBy/{industry}")
	public ResponseEntity<List<Admin>> findByIndustry(@PathVariable String industry){
		return ResponseEntity.status(HttpStatus.OK).body(adminService.findByIndustry(industry));
	}
	
	
	@GetMapping("findBy/{industry}/{role}")
	public ResponseEntity<List<Admin>> findByIndustryAndRole(@PathVariable String industry, @PathVariable String role){
		return ResponseEntity.ok(adminService.findByRoleAndIndustry(role, industry));
	}
	
	@PostMapping("saveMenu")
	public ResponseEntity<List<MenuItem>> saveMenu(@RequestBody List<MenuItem> menuList)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(adminService.save(menuList));
	}
	
	@GetMapping("findMenu")
	public ResponseEntity<List<MenuItem>> findMenu()
	{
	 return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());	
	}
	
	@PostMapping("saveClientInfo")
	public ResponseEntity<ClientInfo> saveClientInfo(@RequestBody ClientInfo clientInfo){
		return ResponseEntity.status(HttpStatus.CREATED).body(adminService.save(clientInfo));
	}
	
}
