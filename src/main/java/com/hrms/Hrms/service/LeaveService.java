package com.hrms.Hrms.service;

import java.util.HashMap;
import java.util.List;

import com.hrms.Hrms.model.Admin;

public interface LeaveService {

	
	Admin leaveploicy(String company);
	
   HashMap<Object, Object> levesCount(Integer empId,String industry);
	
}
