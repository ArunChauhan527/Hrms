package com.hrms.Hrms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.SalaryConfig;
import com.hrms.Hrms.repository.SalaryConfigRepo;

@Service
@Transactional
public class SalaryConfigServiceImpl implements SalaryConfigService {

	@Autowired
	private SalaryConfigRepo repo;
	
	
	@Override
	public SalaryConfig save(SalaryConfig salaryConfig) {
		return repo.save(salaryConfig);
	}

	@Override
	public SalaryConfig findByIndustry(String industry) {
		return repo.findByIndustry(industry);
	}

	@Override
	public List<SalaryConfig> findByIndustries(List<String> industry) {
		return repo.findByIndustryIn(industry);
	}

}
