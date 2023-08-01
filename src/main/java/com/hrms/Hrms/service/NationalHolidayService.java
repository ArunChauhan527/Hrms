package com.hrms.Hrms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrms.Hrms.model.NationalHoliday;

public interface NationalHolidayService {

	/**
	 * @param model
	 * @return
	 */
	List<NationalHoliday> saveAll(List<NationalHoliday> model);

	/**
	 * @param industry
	 * @return
	 */
	List<NationalHoliday> findByIndustry(String industry);
	
	/**
	 * @param file
	 * @param industry
	 * @return
	 */
	List<NationalHoliday> importExcel(MultipartFile file, String industry);
}
