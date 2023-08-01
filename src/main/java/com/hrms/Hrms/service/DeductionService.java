package com.hrms.Hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.hrms.Hrms.model.Deductions;

public interface DeductionService {
	
	/**
	 * @param deduction
	 * @return
	 */
	Deductions save(Deductions deduction);
	
	/**
	 * @param empCode
	 * @return
	 */
	List<Deductions> getByEmpCode(String empCode);

	/**
	 * @param empCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Deductions> getByEmpCodeAndCreatedDate(String empCode, Date startDate, Date endDate);
	
	/**
	 * @param industry
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Deductions> getByIndustry(String industry, int page, int size);
}
