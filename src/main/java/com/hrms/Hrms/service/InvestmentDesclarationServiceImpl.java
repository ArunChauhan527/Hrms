package com.hrms.Hrms.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.model.InvestmentDeclaration;
import com.hrms.Hrms.repository.InvestMentDeclarationRepo;

@Transactional
@Service
public class InvestmentDesclarationServiceImpl implements InvestmentDeclarationService{

	
	@Autowired
	private InvestMentDeclarationRepo descRepo;
	
	@Override
	public InvestmentDeclaration searchByEmpCodeAndSubmittedDate(String empCode, Date startDate, Date endDate) {
		
		return descRepo.findByEmpCodeAndSubmittedDateBetween(empCode, endDate, endDate);
	}

	@Override
	public Page<InvestmentDeclaration> searchByIndustryAndSubmittedDate(String industry, Date startDate, Date endDate,int page, int size) {
		return descRepo.findByIndustryAndSubmittedDateBetween(industry, endDate, endDate, PageRequest.of(page, size));
	}

	@Override
	public InvestmentDeclaration  saveInvestmentDeclaration(InvestmentDeclaration investmentDec) {
		if(0 == investmentDec.getUuid())
		{
		investmentDec.setUpdatedDate(new Date());
		investmentDec.setSubmittedDate(new Date());
		}else
		{
			investmentDec.setUpdatedDate(new Date());
		}
		return descRepo.save(investmentDec);
	}

}
