package com.hrms.Hrms.service;

import java.util.List;

import com.hrms.Hrms.Dto.LeaveCount;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.model.Leave;

public interface LeaveService {
 
	/**
	 * @param empId
	 * @param industry
	 * @return
	 */
	LeaveCount leavesCount(String empId, String industry);
	
	/**
	 * @param empCode
	 * @param industry
	 * @param page
	 * @param size
	 * @return
	 */
	ResponseDataDto findByEmpCodeAndIndustry(String empCode, String industry, Integer page, Integer size);
   
	/**
     * @param model
     * @return
     */
    List<Leave> saveAll(List<Leave> model);
    /**
     * @param model
     * @return
     */
    Leave save(Leave model);
    
    
    /**
     * @param empCode
     * @param industry
     * @return
     */
    Long RequestCount(String empCode, String industry);
}
