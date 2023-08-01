package com.hrms.Hrms.exception;

public class UserDoesNotExist  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6215967012963233425L;
	
	public UserDoesNotExist() {
	   super();
	}
	
	public UserDoesNotExist(String message){
		super(message);
	}

}
