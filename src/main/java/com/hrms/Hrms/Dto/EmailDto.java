package com.hrms.Hrms.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class EmailDto {

	private List<String> email;
	private List<String> department; 
}
