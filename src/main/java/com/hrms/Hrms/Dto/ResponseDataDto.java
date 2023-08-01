package com.hrms.Hrms.Dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResponseDataDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3501255552421342616L;
    
	private Long totalElement;
	
	private List<?> content;
	
	
}
