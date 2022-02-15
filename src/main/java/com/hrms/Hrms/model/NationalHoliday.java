package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="national_holiday")
@Data
public class NationalHoliday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int sno;
	String holiday;
	Date occured_date;
	String region;
	String department;
	
}
