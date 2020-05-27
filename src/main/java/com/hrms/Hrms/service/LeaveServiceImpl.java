package com.hrms.Hrms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.repository.AdminRepository;
import com.hrms.Hrms.repository.LeaveRepository;

@Component("leaves")
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	AdminRepository admin;
	@Autowired
	LeaveRepository leave;
	
	@Override
	public Admin leaveploicy(String company) {
		// TODO Auto-generated method stub
		try{
			
			Admin adminpolicy = admin.getleavePolicy(company);
			
			return adminpolicy;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	@Override
	public HashMap<Object, Object> levesCount(Integer empId, String industry) {
		// TODO Auto-generated method stub
		try{
			
			
			Object[] count =  leave.leavesCount(empId, industry);
			Object[] value  = new ObjectMapper().writeValueAsString(count).replace("[","").replace("]","").replaceAll("\"", "").split(",");
			
			
			HashMap<Object, Object> map = new HashMap<>();
			if(count.length!=0)
			{
			map = toMap(value);
			}
			else
			{
			map.put("leaves", "0");
			}
			return map;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			HashMap<Object,Object>  count = new HashMap<>();
			count.put("message", e.getMessage());
			return count;
		}
		
		
	}
	
	public   HashMap<Object, Object> toMap(Object... entries) {
	    if(entries.length % 2 == 1)
	        throw new IllegalArgumentException("Invalid entries");
	    return (HashMap<Object, Object>)IntStream.range(0, entries.length/2).map(i -> i*2)
	        .collect(HashMap::new, (m,i)->m.put(entries[i], entries[i+1]), Map::putAll);
	}
	

}
