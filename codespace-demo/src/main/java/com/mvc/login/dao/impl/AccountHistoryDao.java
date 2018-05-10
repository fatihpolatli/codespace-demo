package com.mvc.login.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IAccountHistoryDao;
import com.mvc.login.entity.AccountHistory;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.enums.AccountTransactionType;
import com.mvc.login.repository.AccountHistoryRespository;

@Component
public class AccountHistoryDao implements IAccountHistoryDao {

	@Autowired
	AccountHistoryRespository repository;

	private AccountHistory log(Long amount, UserAccount account, AccountTransactionType type) {

		AccountHistory log = new AccountHistory();

		log.setAccount(account);
		log.setType(Integer.parseInt(type.val()));
		log.setAmount(amount);

		return repository.save(log);
	}

	@Override
	public AccountHistory additionLog(Long amount, UserAccount account) {

		return log(amount, account, AccountTransactionType.ADD);
	}

	@Override
	public AccountHistory subtractLog(Long amount, UserAccount account) {

		return log(amount, account, AccountTransactionType.SUBTRACT);
	}

	@Override
	public Set<AccountHistory> findByAccount(UserAccount account) {
		// TODO Auto-generated method stub
		return repository.findByAccountOrderByActionDateDesc(account);
	}

}
