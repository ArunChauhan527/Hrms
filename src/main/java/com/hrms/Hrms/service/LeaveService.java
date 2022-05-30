package com.hrms.Hrms.service;

import java.util.List;

import com.hrms.Hrms.Dto.LeaveCount;
import com.hrms.Hrms.model.Leave;

public interface LeaveService {
   LeaveCount leavesCount(Integer empId, String industry);
	List<Leave> findByEmpCodeAndIndustry(Integer empCode, String industry);
    List<Leave> saveAll(List<Leave> model);
    Leave save(Leave model);
}
