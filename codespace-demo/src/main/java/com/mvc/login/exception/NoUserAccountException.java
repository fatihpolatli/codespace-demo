package com.mvc.login.exception;

public class NoUserAccountException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8097982270220994987L;

	public NoUserAccountException() {

		super("this account couldnt be found");
	}

}
