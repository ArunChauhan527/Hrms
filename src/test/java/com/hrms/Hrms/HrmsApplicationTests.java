package com.hrms.Hrms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.service.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;

import com.hrms.Hrms.controller.TestController;




@SpringBootTest
@ContextConfiguration(classes= {TestController.class, EmailServiceImpl.class, JwtDetailsService.class, TestConfig.class})
public class HrmsApplicationTests {


	@Test
	public void contextLoads() throws Exception {
		get("/FirstApp");
	}

}
