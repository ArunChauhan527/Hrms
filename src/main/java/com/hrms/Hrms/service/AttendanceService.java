package com.hrms.Hrms.service;

import java.util.Date;
import java.util.List;

import com.hrms.Hrms.model.Attendance;

public interface AttendanceService {

	/**
	 * @param attan
	 * @return
	 */
	Attendance save(Attendance attan);
	
	/**
	 * @param id
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Attendance> weeklyAttendnace(String id, Date startDate, Date endDate, String industry, String userName);
	
	/**
	 * @param regulate
	 * @return
	 */
	Attendance regulate(Attendance regulate);
	
	/**
	 * @param industry
	 * @param empCode
	 * @return
	 */
	Attendance today(String empCode);
}
