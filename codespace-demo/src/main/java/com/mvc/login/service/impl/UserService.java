package com.mvc.login.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mvc.login.dao.IAccountHistoryDao;
import com.mvc.login.dao.IUserAccountDao;
import com.mvc.login.dao.IUserDao;
import com.mvc.login.dao.impl.MoneyTypesDao;
import com.mvc.login.dto.BalanceDto;
import com.mvc.login.dto.TransferDto;
import com.mvc.login.dto.UserDto;
import com.mvc.login.entity.AccountHistory;
import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.entity.User;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.entity.UserWithoutPassword;
import com.mvc.login.enums.AccountTransactionType;
import com.mvc.login.exception.AccountAlreadyExistException;
import com.mvc.login.exception.DuplicateEmailException;
import com.mvc.login.exception.NoUserException;
import com.mvc.login.exception.NotEnoughBalanceException;
import com.mvc.login.exception.TransactionActionException;
import com.mvc.login.mock.BalanceLoader;
import com.mvc.login.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserAccountDao userAccountDao;
	
	@Autowired
	private IAccountHistoryDao accountHistoryDao;

	@Autowired
	private MoneyTypesDao moneyTypeDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public User registerNewUserAccount(UserDto accountDto) throws Exception {

		if (emailExist(accountDto.getEmail())) {
			throw new DuplicateEmailException();
		}
		User user = new User();
		user.setUsername(accountDto.getFirstName());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setEmail(accountDto.getEmail());
		return userDao.save(user);
	}

	private boolean emailExist(String email) throws DuplicateEmailException {
		User user = userDao.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public User findByUserName(String username) throws NoUserException {

		return userDao.findByUsername(username);

	}

	@Override
	public User createUserAccount(UserDto accountDto) {
		User registered = null;
		try {
			registered = registerNewUserAccount(accountDto);
		} catch (Exception e) {
			return null;
		}
		return registered;
	}

	@Override
	public Boolean deleteAccount(UserAccount account) throws Exception {
		// TODO Auto-generated method stub
		
		User user = getUserInfo();
		return  userAccountDao.delete(user.getId(), account.getMoneyType().getId());
	}
	
	@Override
	@Transactional
	public User addAcount(UserAccount account) throws Exception {

		User user = getUserInfo();

		Set<UserAccount> accounts = user.getAccounts();

		Long accountTypeId = account.getMoneyType().getId();

		UserAccount existingAccount = null;
		try {
			
			existingAccount  = getAccountInfo(accountTypeId, user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		if (existingAccount != null) {

			throw new AccountAlreadyExistException();
		}

		MoneyTypes moneyType = moneyTypeDao.findById(accountTypeId);

		if (accounts == null) {

			accounts = new HashSet<UserAccount>();

			user.setAccounts(accounts);
		}

		UserAccount userAccount = new UserAccount();

		userAccount.setMoneyType(moneyType);

		UserAccount persistentAccount = userAccountDao.save(userAccount);

		user.getAccounts().add(persistentAccount);

		return userDao.save(user);

	}

	private User getUserInfo() throws Exception {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return findByUserName(username);
	}

	private UserAccount getAccountInfo(Long accountId, Long userId) throws Exception {

		return userAccountDao.findByAccountTypeAndUserId(accountId, userId);
		
	}

	@Override
	public Set<UserAccount> getAccounts() throws NoUserException {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = findByUserName(username);

		return user.getAccounts();

	}

	@Override
	@Transactional
	public Boolean addSubtractBalance(BalanceDto balance) throws Exception {

		User user = getUserInfo();

		return addSubtractBalanceByUser(user, balance);

	}
	
	public Boolean addSubtractBalanceByUser(User user, BalanceDto balance) throws Exception {


		UserAccount userAccount = getAccountInfo(balance.getAccount().getMoneyType().getId(), user.getId());

		Long currentBalance = userAccount.getBalance();

		Long balanceAmount = balance.getAmount();

		if (balance.getType() == AccountTransactionType.ADD) {

			currentBalance = currentBalance + balanceAmount;
			
			accountHistoryDao.additionLog(balanceAmount, userAccount);

		} else if (balance.getType() == AccountTransactionType.SUBTRACT) {

			if (currentBalance >= balanceAmount) {

				currentBalance = currentBalance - balanceAmount;
				
				accountHistoryDao.subtractLog(balanceAmount, userAccount);
			}else {
				
				throw new NotEnoughBalanceException();
			}
		}
		
		userAccount.setBalance(currentBalance);
		
		userAccountDao.save(userAccount);
		
		return true;

	}

	@Override
	public Long getCurrentBalance(BalanceDto balance) throws Exception {
		
		User user = getUserInfo();
		
		UserAccount userAccount = getAccountInfo(balance.getAccount().getMoneyType().getId(), user.getId());
		
		return userAccount.getBalance();
	}
	
	@Override
	public Set<AccountHistory> getAccountHistory(BalanceDto balance) throws Exception {
		
		User user = getUserInfo();
		
		UserAccount userAccount = getAccountInfo(balance.getAccount().getMoneyType().getId(), user.getId());
		
		return accountHistoryDao.findByAccount(userAccount);
	}

	@Override
	public List<UserWithoutPassword> getUserList() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override	
	@Transactional
	public Boolean transferMoney(TransferDto transferData) throws Exception {
		// TODO Auto-generated method stub
		
		User targetUser = userDao.findByUsername(transferData.getTargetUser().getUsername());
		
		BalanceDto balanceData = transferData.getBalance();
		
		
		balanceData.setType(AccountTransactionType.SUBTRACT);
		
		addSubtractBalance(balanceData);
	
		balanceData.setType(AccountTransactionType.ADD);
		
		balanceData.setAccount(transferData.getTargetAccount());
		
		
		addSubtractBalanceByUser(targetUser, balanceData);
		
		
		return true;
	}

	@Override
	public Boolean loadBalance(BalanceDto balance) throws Exception {
		// TODO Auto-generated method stub
		Boolean result = BalanceLoader.loadBalance();
		
		if(result) {
			
			balance.setType(AccountTransactionType.ADD);
			
			return addSubtractBalance(balance);
		}
		
		return false;
	}

	

}