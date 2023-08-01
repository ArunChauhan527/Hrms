package com.hrms.Hrms.exception;

public class InvalidEmail extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6122433607318842528L;

	public InvalidEmail() {
	  super();
	}
	
	public InvalidEmail(String message){
		super(message);
	}
}
