package com.mvc.login.dto;

import com.mvc.login.enums.AccountTransactionType;

public class BalanceDto {
	
	private Long amount;
	
	private UserAccountDto account;
	
	private AccountTransactionType type;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public UserAccountDto getAccount() {
		return account;
	}

	public void setAccount(UserAccountDto account) {
		this.account = account;
	}

	public AccountTransactionType getType() {
		return type;
	}

	public void setType(AccountTransactionType type) {
		this.type = type;
	}

}
