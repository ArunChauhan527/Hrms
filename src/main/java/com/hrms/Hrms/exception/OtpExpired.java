package com.hrms.Hrms.exception;

public class OtpExpired extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1252180804099989893L;

	public OtpExpired() {
	   super();
	}
	
	public OtpExpired(String msg) {
		   super(msg);
		}
	
	
}
