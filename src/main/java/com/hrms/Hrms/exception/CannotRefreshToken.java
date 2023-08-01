package com.hrms.Hrms.exception;

public class CannotRefreshToken  extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8855417888512749717L;

	public CannotRefreshToken() {
	  super();
	}
	
	public CannotRefreshToken(String msg)
	{
		super(msg);
	}
	
}
