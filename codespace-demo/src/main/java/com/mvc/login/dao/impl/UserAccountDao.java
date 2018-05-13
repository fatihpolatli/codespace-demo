package com.mvc.login.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IUserAccountDao;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.exception.NoUserAccountException;
import com.mvc.login.repository.UserAccountRespository;

@Component
@Transactional
public class UserAccountDao implements IUserAccountDao{
	
	@Autowired
	UserAccountRespository repository;

	@Override
	public UserAccount save(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return repository.save(userAccount);
	}

	@Override
	public UserAccount findByAccountType(Long accountTypeId) {
		// TODO Auto-generated method stub
		return repository.findByMoneyTypeId(accountTypeId);
	}

	@Override
	public UserAccount findByAccountTypeAndUserId(Long moneyTypeId, Long userId) throws NoUserAccountException {
		// TODO Auto-generated method stub
		
		UserAccount account = repository.findByMoneyTypeIdAndUserId(moneyTypeId, userId);
		
		if(account == null) {
			
			throw new NoUserAccountException();
		}
		
		return account;
	}

	@Override	
	public Boolean delete(Long userId, Long moneyTypeId) {
		// TODO Auto-generated method stub
		UserAccount persistentData = repository.findOneByUserIdAndMoneyTypeId(userId, moneyTypeId);
		
		persistentData.setIsDeleted(1);
		
		repository.save(persistentData);
		
		return true;
	}

}
