package com.hrms.Hrms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.Earning;

@Repository
public interface EarningRepository extends JpaRepository<Earning, Integer>{
	
   List<Earning> findByEmpCode(String empCode);
   
   List<Earning> findByEmpCodeAndCreatedDateBetween(String empCode, Date startDate, Date endDate);
 	
   Page<Earning> findByIndustry(String industry, Pageable page);	
   
   @Query("select e from  Earning as e where e.industry =?1  and e.empCode = ?2 and month(createdDate) =?3")
   Earning findByIndustryAndEmpCodeAndCreatedDate(String industry, String empCode, Integer createdDate);
}
