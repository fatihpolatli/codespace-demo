package com.mvc.login.dto;

import com.mvc.login.entity.UserAccount;
import com.mvc.login.entity.UserWithoutPassword;

public class TransferDto{

	private UserWithoutPassword targetUser;
	
	private UserAccount targetAccount;
	
	private BalanceDto balance;

	public UserWithoutPassword getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(UserWithoutPassword targetUser) {
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
