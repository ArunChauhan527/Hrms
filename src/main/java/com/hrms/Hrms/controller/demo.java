package com.hrms.Hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




public class demo {

	
	public static boolean checkPhase(List<Set<String>> dictionary, String phase1, String phase2 ) {
		List<String> listPhase1 =  Arrays.asList(phase1.split("\\s"));
		List<String> listPhase2 = Arrays.asList(phase2.split("\\s"));
		boolean flag = true;
		int count =0;
		for(String m: listPhase1) {
			int count1 = count;
			Set<String> matchDic  = dictionary.stream().filter(dic->dic.stream().filter(d->d.equalsIgnoreCase(m)).count()==1L).findFirst().orElse(null);
			String res = matchDic!=null? matchDic.stream().filter(match->match.equalsIgnoreCase(listPhase2.get(count1))).findFirst().orElse(null): null;
			if(StringUtils.isBlank(res))
			{
				flag = false;
				break;
			}
			count++;
		}
		
		return flag;
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException {
		List<Set<String>> dictionary = new ArrayList<>();
		Set<String> d1 = new HashSet<String>();
		d1.add("house");
		d1.add("home");
		
		Set<String> d2 = new HashSet<String>();
		d2.add("Mommy");
		d2.add("mom");
		d2.add("mother");
		
		Set<String> d3 = new HashSet<String>();
		d3.add("in");
		dictionary.add(d3);
		dictionary.add(d2);
		dictionary.add(d1);
		
		String phase1 = "MOTHER is house";
		String phase2 = "Mommy in home";
		System.out.println(checkPhase(dictionary, phase1, phase2));
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1234);
		List<Number> num = new ArrayList<Number>();
		num.add(123456);
		num.add(123456.887);
		num.addAll(numbers);
		num.forEach(n->{
			System.out.println(n);
		});
		BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("password"));
		
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
		
		
		long startDate =  new Date().getTime();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endDate   = new Date().getTime();
		System.out.println("time : "+(endDate-startDate)/(60*1000));
	}
	
}
