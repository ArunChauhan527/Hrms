package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="national_holiday")
@Data@NoArgsConstructor@AllArgsConstructor
public class NationalHoliday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int sno;
	String holiday;
	@Column(name = "occured_date")
	Date occuredDate;
	String region;
	String department;
	String company;
	String industry;
	
}
