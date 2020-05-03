package com.hrms.Hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.Hrms.model.Otp;

@Repository
@Transactional
public interface OtpRepository extends JpaRepository<Otp, Integer> {
	
	
	
	

}
