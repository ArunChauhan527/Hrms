package com.hrms.Hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.Hrms.model.Registration;



@Repository
@Transactional
public interface LoginRepository extends  JpaRepository<Registration, Integer>{

	@Query("Select e from Registration e where e.userName=?1 and e.password=?2")
	Registration getLogin(String username,String password);
	
    @Modifying
	@Query("Update  Registration set password=:password where offical_email_id=:emailid")
	void changePassword(@Param("password")String password,@Param("emailid")String emailid);
    
    Registration findByUserName(@Param("userName") String userName);
	
	
}
