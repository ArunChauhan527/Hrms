package com.hrms.Hrms.service;

import java.util.List;

import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.model.ClientInfo;
import com.hrms.Hrms.model.MenuItem;

public interface AdminService {

	
	/**
	 * @param admin
	 * @return
	 */
	Admin save(Admin admin);
	
	/**
	 * @param id
	 */
	void delete(int id);
	
	/**
	 * @param Sno
	 * @return
	 */
	Admin findById(int Sno);
	
	/**
	 * @param industry
	 * @param page
	 * @param size
	 * @return
	 */
	ResponseDataDto findByIndustry(String industry, Integer page, Integer size);
	
	/**
	 * @param role
	 * @param industry
	 * @return
	 */
	Admin findByRoleAndIndustry(int role, String industry);
	
	/**
	 * @param menuList
	 * @return
	 */
	List<MenuItem> save(List<MenuItem> menuList);
	
	/**
	 * @return
	 */
	List<MenuItem> findAll();
	
	/**
	 * @param roleId
	 * @param industry
	 * @return
	 */
	List<MenuItem> findByRoleIdAndIndustry(int roleId, String industry);
	
	/**
	 * @param clientInfo
	 * @return
	 */
	ClientInfo save(ClientInfo clientInfo);
	
	/**
	 * @return
	 */
	String fetchIndustry();
	
	/**
	 * @param industry
	 * @param roleName
	 * @return
	 */
	Admin findByIndustryAndRoleName(String industry, String roleName);
	/**
	 * @param search
	 * @return
	 */
	List<ClientInfo> fetchClients(String search);
	
	/**
	 * @param searchParam
	 * @param page
	 * @param size
	 * @return
	 */
	ResponseDataDto getClient(String searchParam, int page, int size);
	
	
	/**
	 * @param industry
	 * @param roleName
	 * @param page
	 * @param size
	 * @return
	 */
	ResponseDataDto findByIndustryAndRoleName(String industry, String roleName, Integer page, Integer size);
	
	/**
	 * @return
	 */
	Admin findDefaultRole();
}
