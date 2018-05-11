package com.mvc.login.dto;

import com.mvc.login.entity.UserAccount;

public class TransferDto{

	private UserDto targetUser;
	
	private UserAccount targetAccount;
	
	private BalanceDto balance;

	public UserDto getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(UserDto targetUser) {
		this.targetUser = targetUser;
	}

	public BalanceDto getBalance() {
		return balance;
	}

	public void setBalance(BalanceDto balance) {
		this.balance = balance;
	}

	public UserAccount getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(UserAccount targetAccount) {
		this.targetAccount = targetAccount;
	}
}
