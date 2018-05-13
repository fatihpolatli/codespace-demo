package com.mvc.login.enums;

public enum MoneyTypesEnum {

	TRY("1"), GBP("2");

	private String val;

	MoneyTypesEnum(String val) {
		this.val = val;
	}

	public Long val() {
		return Long.parseLong(val);
	}

}
