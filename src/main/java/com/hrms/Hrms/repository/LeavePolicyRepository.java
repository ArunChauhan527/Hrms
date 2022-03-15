package com.hrms.Hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.LeavePolicy;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Integer>{

	
	List<LeavePolicy> findByIndustry(String industry);

	
}
