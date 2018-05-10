package com.mvc.login.exception;

public class NoCurrencyTypeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8779390166243204157L;

	public NoCurrencyTypeException() {

		super("there is no such a currency");
	}

}
