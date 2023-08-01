package com.hrms.Hrms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.Hrms.model.Registration;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelReaderImpl  implements ExcelReader {

	
	@Autowired
	private LoginService loginService;
	
	
	@Override
	public String excelReader(MultipartFile excelFile, String industry) throws IllegalStateException, IOException, InvalidFormatException {
	
		log.info("fileName : {} ",excelFile.getOriginalFilename());
		
		InputStream fileStream = excelFile.getInputStream();
		log.info("stream : {} ", fileStream.available());
	
		Workbook workBook = WorkbookFactory.create(fileStream);
		Sheet mainSheet = workBook.getSheetAt(0);
		Iterator<Row> rowItr = mainSheet.iterator();
		while(rowItr.hasNext())
		{
			Row row = rowItr.next();
			if(row.getRowNum()!=0)
			{
			log.info("row info {}, {}, {}, {}, {} ", row.getFirstCellNum(), row.getLastCellNum(), row.getRowNum(), row.getPhysicalNumberOfCells(), row.getCell(0));
			Registration reg= new Registration();
			reg.setFirstName(row.getCell(0).getStringCellValue());
			reg.setLastName(row.getCell(1).getStringCellValue());
			reg.setOfficalEmailId(row.getCell(2).getStringCellValue());
			reg.setPersonalEmailId(row.getCell(3).getStringCellValue());
			reg.setDOB(row.getCell(4).getStringCellValue());
			reg.setGender(row.getCell(5).getStringCellValue());
			reg.setJoiningDate(row.getCell(6).getDateCellValue());
			reg.setDesignation(row.getCell(7).getStringCellValue());
			reg.setDepartment(row.getCell(8).getStringCellValue());
			reg.setAddress(row.getCell(9).getStringCellValue());
			reg.setCity(row.getCell(10).getStringCellValue());
			reg.setState(row.getCell(11).getStringCellValue());
			reg.setPincode(Integer.parseInt(row.getCell(12).getStringCellValue()));
			reg.setPancard(row.getCell(13).getStringCellValue());
			reg.setAadharcardno(row.getCell(14).getNumericCellValue());
			reg.setReportingManager(row.getCell(15).getStringCellValue());
			reg.setRoleId(2);
			loginService.getRegister(reg);
		 }
		}
		return "Success";
	}
	
	
}
