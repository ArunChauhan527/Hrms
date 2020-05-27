package com.hrms.Hrms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.Hrms.model.Otp;

@Repository
@Transactional
public interface OtpRepository extends JpaRepository<Otp, Integer> {
	
	 @Modifying
	@Query("Update  Otp set status='checked' where emailid=:emailid and otp=:otp")
	void changePassword(@Param("emailid")String emailid,@Param("otp")String otp);
}
