package com.mvc.login.exception;

public class NoUserException extends Exception {

	public NoUserException() {

		super("there is no such a user");
	}

}
