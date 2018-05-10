package com.mvc.login.exception;

public class DuplicateEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateEmailException() {

		super("this email is already exists");
	}
}
