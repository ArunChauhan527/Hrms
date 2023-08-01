package com.hrms.Hrms.exception;

public class WrongPassword extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -494829629730197206L;

    public WrongPassword() {
		super();
	}		
    
   public  WrongPassword(String msg)
    {
    	super(msg);
    }
}
