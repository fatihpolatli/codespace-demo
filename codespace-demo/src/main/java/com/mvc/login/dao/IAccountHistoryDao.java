package com.mvc.login.dao;

import java.util.Set;

import com.mvc.login.entity.AccountHistory;
import com.mvc.login.entity.UserAccount;

public interface IAccountHistoryDao {

	AccountHistory additionLog(Long amount, UserAccount account);

	AccountHistory subtractLog(Long amount, UserAccount account);

	Set<AccountHistory> findByAccount(UserAccount account);

}
