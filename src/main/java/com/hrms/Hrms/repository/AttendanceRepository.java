package com.hrms.Hrms.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String>{

	List<Attendance> findByEmpCodeAndCreatedAtBetweenOrderByPunchIn(String empCode, Date startDate, Date endDate);
	List<Attendance> findByEmpCodeAndPunchInBetweenOrderByPunchIn(String empCode, Date startDate, Date endDate);
	
	Attendance findByEmpCodeAndPunchInBetween(String empCode, Date punchInStart, Date punchInEnd);
	
	@Query("Select Count(a) from  Attendance a where a.empCode= ?1 and month(a.punchIn)= ?2 and a.industry= ?3 and year(a.punchIn)= ?4 and a.punchOut is not null and a.status= 'Self'")
	Long countByEmpCodeAndMonthAndIndustryAndSelf(String empCode, int month, String industry, int year);
	
	@Query("Select Count(a) from  Attendance a where a.empCode= ?1 and month(a.punchIn)= ?2 and a.industry= ?3 and year(a.punchIn)= ?4 and a.punchOut is not null and a.status= 'Regulate' and a.action='Approve'")
	Long countByEmpCodeAndMonthAndIndustryAndRegulate(String empCode, int month, String industry, int year);
	
}
