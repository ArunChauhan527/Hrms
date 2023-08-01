package com.hrms.Hrms.service;

import java.util.List;

import com.hrms.Hrms.model.SalaryConfig;

public interface SalaryConfigService {
	
	
	/**
	 * @param salaryConfig
	 * @return
	 */
	SalaryConfig save(SalaryConfig salaryConfig);
	
	/**
	 * @param industry
	 * @return
	 */
	SalaryConfig findByIndustry(String industry);
	
	/**
	 * @param industry
	 * @return
	 */
	List<SalaryConfig> findByIndustries(List<String> industry);

}
