package com.mvc.login.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IUserDao;
import com.mvc.login.entity.User;
import com.mvc.login.exception.NoUserException;
import com.mvc.login.repository.UserRepository;

@Component
public class UserDao implements IUserDao{
	
	@Autowired
	UserRepository repository;

	@Override
	public User findByUsername(String username) throws NoUserException {
		// TODO Auto-generated method stub
		
		User user = repository.findByUsername(username);
		
		if(user == null) {
			 
			 throw new NoUserException();
		 }
		return user;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

}
