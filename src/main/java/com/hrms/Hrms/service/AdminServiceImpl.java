package com.hrms.Hrms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.Dto.ClientDto;
import com.hrms.Hrms.Dto.ResponseDataDto;
import com.hrms.Hrms.Dto.StringConstants;
import com.hrms.Hrms.model.Admin;
import com.hrms.Hrms.model.ClientInfo;
import com.hrms.Hrms.model.MenuItem;
import com.hrms.Hrms.model.Registration;
import com.hrms.Hrms.repository.AdminRepository;
import com.hrms.Hrms.repository.ClientInfoRepository;
import com.hrms.Hrms.repository.LoginRepository;
import com.hrms.Hrms.repository.MenuItemRepository;

@Service@Transactional
public class AdminServiceImpl  implements AdminService{

	
	@Autowired
	private AdminRepository adminRep;
	
	@Autowired
	private MenuItemRepository menuRep;
	
	@Autowired
	private ClientInfoRepository clientInfoRepo;
	
	@Autowired
	private LoginRepository loginRep;
	
	
	@PostConstruct
	private void saveAdmin() {
		if(!adminRep.existsById(1))
		{
		Admin admin = new Admin();
		admin.setRoleId(1);
		admin.setAccessModule("9");
		admin.setAccessApproval(true);
		admin.setCreatedBy(StringConstants.sysAdmin);
		admin.setCreatedat(new Date());
		admin.setIndustry(StringConstants.defaultIndustry);
		admin.setUpdatedBy(StringConstants.sysAdmin);
		admin.setRoleName(StringConstants.supAdmin);
		admin.setUpdatedat(new Date());
		adminRep.save(admin);
		List<MenuItem> menuItemList = new ArrayList<>();
		MenuItem  menuItem1= new MenuItem();
		menuItem1.setSno(1);
		menuItem1.setName("DashBoard");
		menuItem1.setRoute("home");
		menuItem1.setToolTip("DashBoard");
		menuItem1.setIcon("home");
		menuItemList.add(menuItem1);
		
		MenuItem  menuItem2= new MenuItem();
		menuItem2.setSno(2);
		menuItem2.setName("Profile");
		menuItem2.setRoute("profile");
		menuItem2.setToolTip("Profile");
		menuItem2.setIcon("face");
		menuItemList.add(menuItem2);
		
		MenuItem  menuItem3= new MenuItem();
		menuItem3.setSno(3);
		menuItem3.setName("Leave");
		menuItem3.setRoute("leave");
		menuItem3.setToolTip("Leave");
		menuItem3.setIcon("schedule");
		menuItemList.add(menuItem3);
		
		MenuItem  menuItem4= new MenuItem();
		menuItem4.setSno(4);
		menuItem4.setName("Attandance");
		menuItem4.setRoute("attandance");
		menuItem4.setToolTip("Attandance");
		menuItem4.setIcon("today");
		menuItemList.add(menuItem4);
		
		MenuItem  menuItem5= new MenuItem();
		menuItem5.setSno(5);
		menuItem5.setName("Payroll");
		menuItem5.setRoute("payroll");
		menuItem5.setToolTip("Payroll");
		menuItem5.setIcon("calculate");
		menuItemList.add(menuItem5);
		
		MenuItem  menuItem6= new MenuItem();
		menuItem6.setSno(6);
		menuItem6.setName("Register");
		menuItem6.setRoute("register");
		menuItem6.setToolTip("Register Employes");
		menuItem6.setIcon("accessibility");
		menuItemList.add(menuItem6);
		
		MenuItem  menuItem8= new MenuItem();
		menuItem8.setSno(7);
		menuItem8.setName("Reimbursement");
		menuItem8.setRoute("reimbursement");
		menuItem8.setToolTip("Reimbursement");
		menuItem8.setIcon("payment");
		menuItemList.add(menuItem8);
		
		MenuItem  menuItem9= new MenuItem();
		menuItem9.setSno(8);
		menuItem9.setName("Settings");
		menuItem9.setRoute("settings");
		menuItem9.setToolTip("Settings");
		menuItem9.setIcon("settings");
		menuItemList.add(menuItem9);
		
		MenuItem  menuItem10= new MenuItem();
		menuItem10.setSno(9);
		menuItem10.setName("Client");
		menuItem10.setRoute("client");
		menuItem10.setToolTip("Client Config");
		menuItem10.setIcon("settings");
		menuItemList.add(menuItem10);
		
		MenuItem  menuItem11= new MenuItem();
		menuItem11.setSno(10);
		menuItem11.setName("Salary Config");
		menuItem11.setRoute("salary");
		menuItem11.setToolTip("Salary Config");
		menuItem11.setIcon("settings");
		menuItemList.add(menuItem11);
		
		menuRep.saveAll(menuItemList);
		}
		
	}
	
	@PostConstruct
	private void saveDefaultRole() {
		Admin admin = new Admin();
		admin.setRoleId(2);
		admin.setAccessModule("1,2,3,4,5");
		admin.setAccessApproval(true);
		admin.setCreatedBy(StringConstants.sysAdmin);
		admin.setCreatedat(new Date());
		admin.setIndustry(StringConstants.defaultIndustry);
		admin.setUpdatedBy(StringConstants.sysAdmin);
		admin.setRoleName(StringConstants.defaultRole);
		admin.setUpdatedat(new Date());
		admin.setDefaultRole(true);
		adminRep.save(admin);
	}
	
	@Override
	public Admin save(Admin admin) {
	return	adminRep.save(admin);
	}

	@Override
	public void delete(int id) {
	 adminRep.deleteById(id);	
	}

	@Override
	public Admin findById(int Sno) {
		Optional<Admin>  admin= adminRep.findById(Sno);
		if(admin.isPresent())
		{
			return admin.get();
		}
		else
		{
			return null;
		}
	}

	@Override
	public ResponseDataDto findByIndustry(String industry, Integer page, Integer size) {
		Page<Admin> admin =  adminRep.findByIndustry(industry, PageRequest.of(page, size, Sort.by("updatedat").descending()));
		ResponseDataDto data =  new ResponseDataDto();
		data.setContent(admin.getContent());
		data.setTotalElement(admin.getTotalElements());
		return data;
	}

	@Override
	public Admin findByRoleAndIndustry(int role, String industry) {
		return adminRep.findByRoleIdAndIndustry(role, industry);
	}

	@Override
	public List<MenuItem> save(List<MenuItem> menuList) {
		return menuRep.saveAll(menuList);
	}

	@Override
	public List<MenuItem> findAll() {
		return menuRep.findAll(Sort.by(StringConstants.sno));
	}

	@Override
	public ClientInfo save(ClientInfo clientInfo) {
		Admin admin = new Admin();
		admin.setAccessApproval(true);
		admin.setRoleName(StringConstants.hrRole);
		admin.setIndustry(clientInfo.getIndustry());
		admin.setCreatedBy(StringConstants.sysAdmin);
		admin.setCreatedat(new Date());
		admin.setUpdatedBy(StringConstants.sysAdmin);
		admin.setUpdatedat(new Date());
		admin.setAccessModule("1,2,3,4,5,6,7,8");
		adminRep.save(admin);
		return clientInfoRepo.save(clientInfo);
	}

	@Override
	public String fetchIndustry() {
		  Random random = new Random();
		 return String.format("%04d", random.nextInt(10000));
	}

	@Override
	public List<MenuItem> findByRoleIdAndIndustry(int roleId, String industry) {
		Admin admin =  adminRep.findByRoleIdAndIndustry(roleId, industry);
		admin = admin == null? adminRep.findByRoleIdAndIndustry(roleId, StringConstants.defaultIndustry) :admin; 
		List<Integer>  moduelIds =  Arrays.asList(admin.getAccessModule().split(",")).stream().map(s->Integer.parseInt(s)).collect(Collectors.toList());
		return menuRep.findAllById(moduelIds);
	}

	@Override
	public List<ClientInfo> fetchClients(String client) {
		return clientInfoRepo.findByCompanyContainsOrderByCompany(client);
	}

	@Override
	public Admin findByIndustryAndRoleName(String industry, String roleName) {
		return adminRep.findByIndustryAndRoleName(industry, roleName);
	}

	@Override
	public ResponseDataDto getClient(String searchParam, int page, int size) {
		ResponseDataDto resdto = new ResponseDataDto();
		List<ClientDto> clientDto = new ArrayList<>();
		Page<ClientInfo> client =  clientInfoRepo.findByCompanyContainsOrderByCompany(searchParam, PageRequest.of(page, size));
		client.getContent().stream().forEach(cl->{	
		Admin admin = adminRep.findByIndustryAndRoleName(cl.getIndustry(), StringConstants.hrRole);
		Registration reg = loginRep.findTop1ByRoleId(admin.getRoleId());
		ClientDto dto = new ClientDto();
		dto.setClientId(cl.getClientId());
		dto.setCompany(cl.getCompany());
		dto.setIndustry(cl.getIndustry());
		dto.setShortForm(cl.getShortForm());
		dto.setCreatedAt(cl.getCreatedAt());
		dto.setHrAdmin(reg!=null? reg.getFirstName() + " "+ reg.getLastName():"");
		dto.setCreateAt(reg!=null?reg.getCreatedDate():null);
		clientDto.add(dto);
		});
		resdto.setContent(clientDto);
		resdto.setTotalElement(client.getTotalElements());
		 return resdto;
	}

	@Override
	public ResponseDataDto findByIndustryAndRoleName(String industry, String roleName, Integer page, Integer size) {
	
		 Page<Admin> admin =  adminRep.findByIndustryAndRoleNameContains(industry, roleName, PageRequest.of(page, size, Sort.by("updatedat").descending()));
		 ResponseDataDto data = new ResponseDataDto();
		 data.setContent(admin.getContent());
		 data.setTotalElement(admin.getTotalElements());
		 return data;
	}

	@Override
	public Admin findDefaultRole() {
		return adminRep.findByDefaultRoleTrue();
	}

	
	
	
	
	
}
