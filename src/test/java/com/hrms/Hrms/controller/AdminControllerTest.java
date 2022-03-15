package com.hrms.Hrms.controller;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.service.AdminService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

	@MockBean
	private AdminService adminService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void saveAdmin() throws Exception {
		
		Admin admin = spy(Admin.class);
		when(adminService.save(admin)).thenReturn(admin);
		ObjectMapper  obj  = new ObjectMapper();
		String json = obj.writeValueAsString(admin);
		this.mockMvc.perform(post("/save").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201));
	}
	
}
