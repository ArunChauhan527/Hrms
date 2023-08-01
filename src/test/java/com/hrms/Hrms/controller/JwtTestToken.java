package com.hrms.Hrms.controller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hrms.Hrms.config.JwtRequestFilter;


@Profile("test")
@Configuration
public class JwtTestToken {

	@Bean
	public JwtRequestFilter mockJwtToken() {
		return Mockito.mock(JwtRequestFilter.class);
	}
}
