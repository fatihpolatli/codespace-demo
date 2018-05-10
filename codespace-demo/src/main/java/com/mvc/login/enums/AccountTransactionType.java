package com.mvc.login.enums;

public enum AccountTransactionType {

	ADD("1"), SUBTRACT("0");

	private String val;

	AccountTransactionType(String val) {
		this.val = val;
	}

	public String val() {
		return val;
	}

}
