package com.hrms.Hrms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.ClientInfo;

@Repository@Transactional
public interface ClientInfoRepository  extends JpaRepository<ClientInfo, Integer>{
	

	List<ClientInfo> findByCompanyContainsOrderByCompany(String name);
	
	Page<ClientInfo> findByCompanyContainsOrderByCompany(String name, Pageable page);
	
	ClientInfo findByIndustry(String industry);
	
}
