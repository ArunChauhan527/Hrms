package com.hrms.Hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.Deductions;
import com.hrms.Hrms.repository.DeductionRepository;

@Service
public class DeductionServiceImpl implements DeductionService {

	@Autowired
	private DeductionRepository deductionRepo;
	
	@Override
	public Deductions save(Deductions deduction) {
		return deductionRepo.save(deduction);
	}

	@Override
	public List<Deductions> getByEmpCode(String empCode) {
		return deductionRepo.findByEmpCode(empCode);
	}

	@Override
	public List<Deductions> getByEmpCodeAndCreatedDate(String empCode, Date startDate, Date endDate) {
		return deductionRepo.findByEmpCodeAndCreatedDateBetween(empCode, startDate, endDate);
	}

	@Override
	public Page<Deductions> getByIndustry(String industry, int page, int size) {
		return deductionRepo.findByIndustry(industry, PageRequest.of(page, size));
	}

	
}
