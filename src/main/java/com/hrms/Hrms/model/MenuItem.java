package com.hrms.Hrms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menuItem")
@Data@NoArgsConstructor@AllArgsConstructor
public class MenuItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sno;
	private String name;
	private String route;
	private String toolTip;
	private String icon;
	
}
