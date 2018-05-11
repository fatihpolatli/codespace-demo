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
		
		UserAccount ac = new UserAccount();
		ac.setMoneyType(tt);
		
		UserAccount acc = userAccountDao.save(ac);
		
		List<UserAccount> ls = new ArrayList<UserAccount>();
		
		ls.add(acc);
		
		
		
		try {
			userService.registerNewUserAccount(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			User us = userDao.findByUsername(u.getFirstName());
			
			us.getAccounts().addAll(ls);
			
			userDao.save(us);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
