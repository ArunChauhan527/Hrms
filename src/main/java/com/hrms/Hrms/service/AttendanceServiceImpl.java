package com.hrms.Hrms.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.Hrms.Enum.AttandanceStatus;
import com.hrms.Hrms.model.Attendance;
import com.hrms.Hrms.repository.AttendanceRepository;

import lombok.extern.slf4j.Slf4j;

@Service@Slf4j
public class AttendanceServiceImpl implements AttendanceService
{
	
	@Autowired
	private AttendanceRepository attandanceRepo;

	@Override
	public Attendance save(Attendance attan) {
		
		attan.setValue(attan.getPunchOut()!=null?(attan.getPunchOut().getTime()-attan.getPunchIn().getTime())/3600000:0);
		return attandanceRepo.save(attan);
	}

	@Override
	public List<Attendance> weeklyAttendnace(String id, Date startDate, Date endDate, String industry, String userName) {
		List<Attendance> att = attandanceRepo.findByEmpCodeAndPunchInBetweenOrderByPunchIn(id, startDate, endDate);
		//long timeDiff = Math.abs(endDate.getTime()-startDate.getTime());
		//long days = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		att = att!=null ? att: new ArrayList<>();
		Date tmpDate = att.size()!=0? att.stream().map(Attendance::getPunchIn).min(Date:: compareTo).get(): endDate;
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(tmpDate);
		List<Calendar> already = new ArrayList<>();
		att.stream().forEach(at->{
		Date date =	at.getPunchIn()!=null?at.getPunchIn(): at.getPunchOut();
		Calendar all =  Calendar.getInstance();
		all.setTime(date);
		already.add(all);
		});
		log.info("start date time {} Day {} , Month {} ", start);
		while(start.get(Calendar.DAY_OF_MONTH)<=end.get(Calendar.DAY_OF_MONTH) ||(start.get(Calendar.DAY_OF_MONTH)+1>end.get(Calendar.DAY_OF_MONTH)+1 &&start.get(Calendar.MONTH)+1<end.get(Calendar.MONTH)+1))
		{
	      	if(already.stream().filter(cal->cal.get(Calendar.DAY_OF_MONTH) == start.get(Calendar.DAY_OF_MONTH)).count()==0)
	      	{
			att.add(createAttendnace(id, industry, userName, startDate));
	      	}
	      	start.add(Calendar.DAY_OF_MONTH, 1);
	      	startDate = start.getTime();
	      	
		}
		
		 return att.stream().sorted(Comparator.comparing(Attendance::getPunchIn)).collect(Collectors.toList());
	}
	
	private Attendance createAttendnace(String id, String industry, String createdBy, Date createdDate) {
		Attendance att = new Attendance();
		att.setCreatedAt(createdDate);
		att.setCreatedBy(createdBy);
		att.setIndustry(industry);
		att.setPunchIn(createdDate);
		att.setEmpCode(id);
		att.setUpdatedBy(createdBy);
		att.setUpdatedAt(createdDate);
		att.setStatus(AttandanceStatus.system);
		return att;
	}

	@Override
	public Attendance regulate(Attendance regulate) {
		regulate.setValue(regulate.getPunchOut()!=null?(regulate.getPunchOut().getTime()-regulate.getPunchIn().getTime())/3600000:0);
		Calendar cal = Calendar.getInstance();
		cal.setTime(regulate.getPunchIn());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date punchInStart = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		Date punchInOut = cal.getTime();
		log.info("log time punchInstart : {} , punchInOut {}", punchInStart, punchInOut);
	     Attendance att = attandanceRepo.findByEmpCodeAndPunchInBetween(regulate.getEmpCode(), punchInStart, punchInOut);
	    if(att!=null)
	    {
	     regulate.setAttId(att.getAttId());
	    }
	     regulate = attandanceRepo.save(regulate);
		return regulate;
	}

	@Override
	public Attendance today(String empCode) {
	   Date endDate = new Date();
	   Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	   return attandanceRepo.findByEmpCodeAndPunchInBetween(empCode, startDate, endDate);
	}

}
