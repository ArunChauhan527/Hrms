package com.hrms.Hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.Hrms.model.NationalHoliday;

@Repository
public interface NationalholidayRepository extends JpaRepository<NationalHoliday, Integer> {

	List<NationalHoliday> findByIndustryOrderByOccuredDate(String industry);
}
