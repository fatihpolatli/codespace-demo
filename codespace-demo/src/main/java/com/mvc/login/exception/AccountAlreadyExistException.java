package com.mvc.login.exception;

public class AccountAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4269529182141276748L;

	public AccountAlreadyExistException() {

		super("there is already an account with this currency");
	}
}
