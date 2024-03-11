package com.hrms.Hrms.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.Enum.LeaveStatus;
import com.hrms.Hrms.Enum.LeaveType;
import com.hrms.Hrms.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

	@Query(value="Select l.type as type , Count(l.type) as count from leaves as l where l.emp_code=?1 and l.industry=?2 group by l.type",nativeQuery=true)
	Object[] leavesCount(Integer empId,String industry);

	Page<Leave> findByEmpCodeAndIndustry(String empCode, String compCode, Pageable page);

	long countByEmpCodeAndIndustryAndStatus(String empCode, String compCode, LeaveStatus status);

	long countByEmpCodeAndIndustryAndType(String empCode, String compCode, LeaveType type);
	
	@Query("Select sum(l.noLeave) from Leave l where empCode= ?1 and industry= ?2 and type!='LWP' and status='approved' and month(fromDate)=?3 and year(fromDate)=?4")
	Long countByEmpCodeAndIndustryAndTypeAndStatusAndMonthFromDate(String empCode, String compCode, int month, int year);
	
	@Query("Select sum(l.noLeave) from Leave l where empCode= ?1 and industry= ?2 and type='LWP' and status='approved' and month(fromDate)=?3 and year(fromDate)=?4")
	Long countByEmpCodeAndIndustryAndTypeLWPAndStatusAndMonthFromDate(String empCode, String compCode, int month, int year);
}
