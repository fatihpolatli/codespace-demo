package com.mvc.login.dao;

import com.mvc.login.entity.UserAccount;
import com.mvc.login.exception.NoUserAccountException;

public interface IUserAccountDao {

	UserAccount save(UserAccount userAccount);

	UserAccount findByAccountType(Long accountTypeId);

	UserAccount findByAccountTypeAndUserId(Long accountId, Long userId) throws NoUserAccountException;

	Boolean delete(Long userId, Long moneyTypeId);

}
