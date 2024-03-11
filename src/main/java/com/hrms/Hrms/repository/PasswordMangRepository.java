package com.hrms.Hrms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.PasswordMang;

@Repository
public interface PasswordMangRepository extends JpaRepository<PasswordMang, Integer> {

	
	PasswordMang findByEmpCode(String empCode);
	
	@Modifying
	@Query("Update  PasswordMang set password=:password where empCode=:empCode")
	void changePassword(@Param("password")String password,@Param("empCode")String empCode);
    
	
}
