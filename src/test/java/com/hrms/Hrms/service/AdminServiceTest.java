package com.hrms.Hrms.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.repository.AdminRepository;

@SpringBootTest
public class AdminServiceTest {

    @MockBean
    AdminRepository adminRep;

    @Autowired
    AdminService adminService;

   
    @DisplayName("save --> save service to save admin config")
    @Test
    void save() {
    	Admin admin = spy(Admin.class);
    	when(adminRep.save(admin)).thenReturn(admin);
    	Admin actual  = adminService.save(admin);
    	assertNotNull(actual);
    	
    }
}
