package com.hrms.Hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.hrms.Hrms.model.Earning;

public interface EarningService {

	/**
	 * @param earning
	 * @return
	 */
	Earning saving(Earning earning);
	
	/**
	 * @param empCode
	 * @return
	 */
	List<Earning> getByEmpCode(String empCode);
	
	/**
	 * @param industry
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Earning> getByIndustry(String industry, int page, int size);
	
	/**
	 * @param empCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Earning> getByEmpCodeAndCreatedDate(String empCode, Date startDate, Date endDate);
	

	/**
	 * @param industry
	 * @param empCode
	 * @param createdDate
	 * @return
	 */
	Earning getEarningByIndustryandEmpCodeAndCreatedDate(String industry, String empCode, Date createdDate); 
	
	
	/**
	 * @param Month
	 * @param empCode
	 * @param industry
	 * @param year
	 */
	void getMonthlyEarning(int Month, String empCode, String industry, int year);
	
}	