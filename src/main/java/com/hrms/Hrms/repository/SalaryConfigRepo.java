package com.hrms.Hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.SalaryConfig;

@Repository
public interface SalaryConfigRepo  extends JpaRepository<SalaryConfig, Integer>{
	
	SalaryConfig findByIndustry(String industry);
	List<SalaryConfig> findByIndustryIn(List<String> industry);

}
