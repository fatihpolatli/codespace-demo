package com.mvc.login.service;

import java.util.List;
import java.util.Set;

import com.mvc.login.dto.BalanceDto;
import com.mvc.login.dto.TransferDto;
import com.mvc.login.dto.UserDto;
import com.mvc.login.entity.AccountHistory;
import com.mvc.login.entity.User;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.entity.UserWithoutPassword;
import com.mvc.login.exception.NoUserException;

public interface IUserService {
	
	User registerNewUserAccount(UserDto accountDto)     
		      throws Exception;

	User findByUserName(String username) throws NoUserException;

	User createUserAccount(UserDto accountDto);

	User addAcount(UserAccount account) throws Exception;

	Set<UserAccount> getAccounts() throws NoUserException;

	Boolean addSubtractBalance(BalanceDto balance) throws Exception;

	Long getCurrentBalance(BalanceDto balance) throws Exception;

	Set<AccountHistory> getAccountHistory(BalanceDto balance) throws Exception;

	List<UserWithoutPassword> getUserList();

	Boolean transferMoney(TransferDto transferData) throws Exception;

	Boolean loadBalance(BalanceDto balance) throws Exception;

	Boolean deleteAccount(UserAccount account) throws Exception;

}
