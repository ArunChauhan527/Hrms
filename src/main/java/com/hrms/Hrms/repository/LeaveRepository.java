package com.hrms.Hrms.repository;

import java.util.List;

import com.hrms.Hrms.Enum.LeaveStatus;
import com.hrms.Hrms.Enum.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

	@Query(value="Select l.type as type , Count(l.type) as count from leaves as l where l.emp_code=?1 and l.industry=?2 group by l.type",nativeQuery=true)
	Object[] leavesCount(Integer empId,String industry);

	List<Leave> findByEmpCodeAndCompCode(Integer empCode, String compCode);

	long countByEmpCodeAndCompCodeAndStatus(Integer empCode, String compCode, LeaveStatus status);

	long countByEmpCodeAndCompCodeAndType(Integer empCode, String compCode, LeaveType type);
}
