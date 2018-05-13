package com.mvc.login.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IUserAccountDao;
import com.mvc.login.dao.IUserDao;
import com.mvc.login.dto.UserDto;
import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.entity.User;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.service.IMoneyTypesService;
import com.mvc.login.service.IUserService;

@Component
public class DataInitialize {

	@Autowired
	IMoneyTypesService service;

	@Autowired
	IUserService userService;

	@Autowired
	IUserDao userDao;

	@Autowired
	IUserAccountDao userAccountDao;

	@PostConstruct
	private void init() {

		MoneyTypes t = new MoneyTypes();
		t.setId(1L);
		t.setMoneyLabel("TRY");

		MoneyTypes g = new MoneyTypes();
		g.setId(2L);
		g.setMoneyLabel("GBP");

		MoneyTypes tt = service.save(t);
		MoneyTypes gg = service.save(g);

		UserDto u = new UserDto();

		u.setEmail("t@t.com");
		u.setFirstName("test1234");
		u.setPassword("Test200.");
		u.setMatchingPassword("Test200.");
		
		UserDto u2 = new UserDto();

		u2.setEmail("t2@t.com");
		u2.setFirstName("test12345");
		u2.setPassword("Test200.");
		u2.setMatchingPassword("Test2000.");

		UserAccount ac = new UserAccount();
		ac.setMoneyType(tt);
		
		UserAccount ac2 = new UserAccount();
		ac.setMoneyType(tt);

		UserAccount acc = userAccountDao.save(ac);

		
		UserAccount acc2 = userAccountDao.save(ac2);

		List<UserAccount> ls = new ArrayList<UserAccount>();

		ls.add(acc);
		
		List<UserAccount> ls2 = new ArrayList<UserAccount>();

		ls2.add(acc2);

		User registeredUser = null;
		

		try {
			registeredUser = userService.registerNewUserAccount(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		registeredUser.getAccounts().addAll(ls);

		User result = userDao.save(registeredUser);
		
		User registeredUser2 = null;

		
		try {
			registeredUser2 = userService.registerNewUserAccount(u2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		registeredUser2.getAccounts().addAll(ls2);

		User result2 = userDao.save(registeredUser2);
		

	}

}
