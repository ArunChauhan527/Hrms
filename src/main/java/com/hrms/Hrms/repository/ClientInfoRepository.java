package com.hrms.Hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.ClientInfo;

@Repository
public interface ClientInfoRepository  extends JpaRepository<ClientInfo, String>{

}
