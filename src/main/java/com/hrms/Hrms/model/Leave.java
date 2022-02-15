package com.hrms.Hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="leaves")
@Data
public class Leave {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer sno;
    @Column(name = "emp_code")	
	Integer empCode;
	String type;
	String status;
    Date appliedon;
    String applied_by;
    Date approved_on;
    String approved_by;
    String updated_by;
    Date updated_on;
    String industry;
    String comp_code;
	
}
