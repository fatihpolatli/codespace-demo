package com.mvc.login.dto;

import javax.validation.constraints.NotNull;

public class UserAccountDto {

	
	@NotNull
    private Long id;
	
	private Long balance;
	
	private MoneyTypesDto moneyType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public MoneyTypesDto getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(MoneyTypesDto moneyType) {
		this.moneyType = moneyType;
	}
	
	
	
}
