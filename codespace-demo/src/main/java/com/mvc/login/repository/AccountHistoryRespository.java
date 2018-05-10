package com.mvc.login.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.login.entity.AccountHistory;
import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.entity.UserAccount;

public interface AccountHistoryRespository extends JpaRepository<AccountHistory, Long> {


	Set<AccountHistory> findByAccount(UserAccount account);

	Set<AccountHistory> findByAccountOrderByActionDateDesc(UserAccount account);
	 
   
}
