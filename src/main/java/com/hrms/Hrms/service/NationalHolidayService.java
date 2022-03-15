package com.hrms.Hrms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrms.Hrms.model.NationalHoliday;

public interface NationalHolidayService {

	List<NationalHoliday> saveAll(List<NationalHoliday> model);

	List<NationalHoliday> findByIndustry(String industry);
	
	List<NationalHoliday> importExcel(MultipartFile file, String industry, String company);
}
