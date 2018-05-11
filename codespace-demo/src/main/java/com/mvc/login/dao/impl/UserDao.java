package com.mvc.login.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IUserDao;
import com.mvc.login.entity.User;
import com.mvc.login.entity.UserWithoutPassword;
import com.mvc.login.exception.NoUserException;
import com.mvc.login.repository.UserRepository;
import com.mvc.login.repository.UserWithoutPasswordRepository;

@Component
public class UserDao implements IUserDao{
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserWithoutPasswordRepository userWithoutPasswordRepository;

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

	@Override
	public List<UserWithoutPassword> findAll() {
		// TODO Auto-generated method stub
		return userWithoutPasswordRepository.findAll();
	}

}
