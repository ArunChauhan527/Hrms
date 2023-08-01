package com.hrms.Hrms.service;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelReader {

	/**
	 * @param excelFile
	 * @param industry
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	/**
	 * @return
	 */
	public String excelReader(MultipartFile excelFile, String industry) throws IllegalStateException, IOException, InvalidFormatException;
	
}
