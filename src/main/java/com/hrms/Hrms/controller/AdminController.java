package com.hrms.Hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.Dto.ResponseDto;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.model.ClientInfo;
import com.hrms.Hrms.model.MenuItem;
import com.hrms.Hrms.service.AdminService;

@RestController
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private JwtDetailsService jwt;
	
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
	
	@GetMapping("findBy")
	public ResponseEntity<ResponseDataDto> findByIndustry(@RequestParam("page") Integer page, @RequestParam("size")Integer size){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.status(HttpStatus.OK).body(adminService.findByIndustry(industry, page, size));
	}
	
	
	@GetMapping("findBy/{role}")
	public ResponseEntity<Admin> findByIndustryAndRole(@PathVariable int role){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.ok(adminService.findByRoleAndIndustry(role, industry));
	}
	
	@GetMapping("findRole")
	public ResponseEntity<ResponseDataDto> findByIndustryAndRoleName(@RequestParam("roleName") String roleName, 	@RequestParam("page") Integer page, @RequestParam("size") Integer size)
	{
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
		return ResponseEntity.status(HttpStatus.OK).body(adminService.findByIndustryAndRoleName(industry, roleName, page, size));
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
	
	@GetMapping("fetchIndustry")
	public ResponseEntity<String> fetchIndustry()
	{
		return ResponseEntity.status(HttpStatus.OK).body(adminService.fetchIndustry());
	}
		
	
	@GetMapping("getModules/{roleId}")
	public ResponseEntity<List<MenuItem>> getModules(@PathVariable int roleId){
		JwtDetials smpleInd = jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String industry = smpleInd.getIndustry();
	   return ResponseEntity.status(HttpStatus.OK).body(adminService.findByRoleIdAndIndustry(roleId, industry));
	}
	
	@GetMapping("getClient/{client}")
	public ResponseEntity<List<ClientInfo>> getAllClient(@PathVariable String client)
	{
		return ResponseEntity.status(HttpStatus.OK).body(adminService.fetchClients(client));
	}
	
	@GetMapping("getClient")
	public ResponseEntity<ResponseDataDto> getClient(@RequestParam("searchParam") String searchParam, @RequestParam("page") int Page, @RequestParam("size") int size)
	{
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getClient(searchParam, Page, size));
	}
	
}
