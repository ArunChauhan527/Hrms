package com.hrms.Hrms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.Hrms.Dto.EmployeeName;
import com.hrms.Hrms.model.Registration;



@Repository
@Transactional
public interface LoginRepository extends  JpaRepository<Registration, String>{

    Registration findByUserNameOrOfficalEmailId(@Param("userName") String userName, String offEmail);
    
    Registration findTop1ByRoleId(int roleId);
    
    Registration findByOfficalEmailId(String OffEmail);
    
    Page<Registration> findByIndustry(String industry, Pageable page);
    
    Registration findByUserNameAndIndustry(String userName, String Industry);	
    
    List<Registration> findByIndustry(String industry);
    
    List<EmployeeName> findByUserNameContainsAndIndustry(String name,String industry);
	
}
