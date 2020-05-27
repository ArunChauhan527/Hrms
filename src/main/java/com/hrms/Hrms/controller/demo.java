package com.hrms.Hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;




public class demo {

	public static void main(String[] args) throws ParseException {
		
		Date date = new Date();
		Date now = Calendar.getInstance().getTime();
		SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm:ss");
		String curDate = sdf.format(date);
		String nowt = sdf.format(now);
		Date otp_time = sdf.parse("04:30:00");
		Date cur_time = sdf.parse("04:15:00");
		System.out.println(otp_time);
		System.out.println(cur_time);
		System.out.println(otp_time.compareTo(cur_time));
		if(otp_time.compareTo(cur_time)>=0)
		{
			System.out.println("success");
		}
		else 
		{
			System.out.println("error");
		}
		
	}
	
}
