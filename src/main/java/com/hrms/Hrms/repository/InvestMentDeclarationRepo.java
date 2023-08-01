package com.hrms.Hrms.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.InvestmentDeclaration;

@Repository
public interface InvestMentDeclarationRepo extends JpaRepository<InvestmentDeclaration, Integer> {
	
	InvestmentDeclaration findByEmpCodeAndSubmittedDateBetween(String empCode, Date startedDate, Date endDate);
	
	Page<InvestmentDeclaration> findByIndustryAndSubmittedDateBetween(String industry, Date startedDate, Date endDate, Pageable page);

}
