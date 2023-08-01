package com.hrms.Hrms.exception;

public class InvalidOtp extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -991843657349320159L;

	public InvalidOtp() {
	  super();
	}
	
	public InvalidOtp(String msg) {
		  super(msg);
		}
}
