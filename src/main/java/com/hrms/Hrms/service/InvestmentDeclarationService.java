package com.hrms.Hrms.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.hrms.Hrms.model.InvestmentDeclaration;

public interface InvestmentDeclarationService {

	/**
	 * @param empCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	InvestmentDeclaration searchByEmpCodeAndSubmittedDate(String empCode, Date startDate, Date endDate);
	
	/**
	 * @param industry
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param size
	 * @return
	 */
	Page<InvestmentDeclaration> searchByIndustryAndSubmittedDate(String industry, Date startDate, Date endDate, int page, int size);
	
	
	/**
	 * @param investmentDec
	 * @return
	 */
	InvestmentDeclaration saveInvestmentDeclaration(InvestmentDeclaration investmentDec);
}

