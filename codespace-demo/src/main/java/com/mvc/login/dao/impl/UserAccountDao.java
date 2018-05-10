package com.mvc.login.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IUserAccountDao;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.repository.UserAccountRespository;

@Component
public class UserAccountDao implements IUserAccountDao{
	
	@Autowired
	UserAccountRespository repository;

	public UserAccount save(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return repository.save(userAccount);
	}

	public UserAccount findByAccountType(Long accountTypeId) {
		// TODO Auto-generated method stub
		return repository.findByMoneyTypeId(accountTypeId);
	}

}
