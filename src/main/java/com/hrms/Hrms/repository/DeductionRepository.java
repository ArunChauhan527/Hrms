package com.hrms.Hrms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.Deductions;

@Repository
public interface DeductionRepository extends JpaRepository<Deductions, Integer>{

	   List<Deductions> findByEmpCode(String empCode);
	   
	   List<Deductions> findByEmpCodeAndCreatedDateBetween(String empCode, Date startDate, Date endDate);
	 	
	   Page<Deductions> findByIndustry(String industry, Pageable page);
	
}
