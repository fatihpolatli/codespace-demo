package com.mvc.login.dto;

import javax.validation.constraints.NotNull;

public class MoneyTypesDto {
	
	@NotNull
    private Long id;
	
	private String moneyLabel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMoneyLabel() {
		return moneyLabel;
	}

	public void setMoneyLabel(String moneyLabel) {
		this.moneyLabel = moneyLabel;
	}

	
	

}
