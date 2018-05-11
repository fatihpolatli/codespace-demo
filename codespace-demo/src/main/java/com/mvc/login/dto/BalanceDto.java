package com.mvc.login.dto;

import com.mvc.login.entity.UserAccount;
import com.mvc.login.enums.AccountTransactionType;

public class BalanceDto {
	
	private Long amount;
	
	private UserAccount account;
	
	private AccountTransactionType type;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	public AccountTransactionType getType() {
		return type;
	}

	public void setType(AccountTransactionType type) {
		this.type = type;
	}

}
