package com.hrms.Hrms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.hrms.Hrms.controller.TestController;




@SpringBootTest
@ContextConfiguration(classes= TestController.class)
public class HrmsApplicationTests {
	
	
	@Test
	public void contextLoads() throws Exception {
		get("/FirstApp");
	}

}
