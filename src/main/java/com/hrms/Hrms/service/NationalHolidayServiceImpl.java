package com.hrms.Hrms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.Hrms.model.NationalHoliday;
import com.hrms.Hrms.repository.NationalholidayRepository;

import lombok.extern.slf4j.Slf4j;

@Service@Slf4j
public class NationalHolidayServiceImpl implements NationalHolidayService{

	@Autowired
	private NationalholidayRepository nationalHolidayRep;
	
	@Override
	public List<NationalHoliday> saveAll(List<NationalHoliday> model) {
		return nationalHolidayRep.saveAll(model);
	}

	@Override
	public List<NationalHoliday> findByIndustry(String industry) {
		return nationalHolidayRep.findByIndustryOrderByOccuredDate(industry);
	}

	@Override
	public List<NationalHoliday> importExcel(MultipartFile file, String industry, String company) {
		List<NationalHoliday> response  = new ArrayList<NationalHoliday>();
		Workbook workBook = null;
		try {
			workBook = WorkbookFactory.create(file.getInputStream());
			log.info("Number of sheets: {} ", workBook.getNumberOfSheets());
		   
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			workBook.forEach(sheet->{
				log.info("sheet Name : {}", sheet.getSheetName());
				for(Row row :sheet) {
					if(row.getRowNum()==0)continue;
					NationalHoliday model = new NationalHoliday();
					model.setHoliday(row.getCell(0).toString());
					try {
						model.setOccuredDate(sdf.parse(row.getCell(1)+""));
					} catch (ParseException e) {
						log.error("error while fetching occured_date {}", row.getCell(1));
						model.setOccuredDate(null);
					}
					model.setRegion(row.getCell(2).toString());
					model.setDepartment(row.getCell(3).toString());
					model.setCompany(company);
					model.setIndustry(industry);
					response.add(model);
				}
				log.info("model value {}", response);
			});
			if (response.size() != 0) {
				saveAll(response);
			}
		}catch (Exception e) {
			log.error("Exception while reading file : {} ", e);
		}
		return response;
	}

}
