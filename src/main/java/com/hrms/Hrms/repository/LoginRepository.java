package com.hrms.Hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.Hrms.model.Registration;



@Repository
@Transactional
public interface LoginRepository extends  JpaRepository<Registration, Integer>{

	@Query("Select e from Registration e where e.user_name=?1 and e.password=?2")
	Registration getLogin(String username,String password);
	
	
}
