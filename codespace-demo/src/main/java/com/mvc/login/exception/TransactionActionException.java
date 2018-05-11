package com.mvc.login.exception;

public class TransactionActionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 155165998382569842L;

	public TransactionActionException() {

		super("an error occured while transfering money");
	}

}
