package com.mvc.login.service;

import com.mvc.login.dto.UserDto;
import com.mvc.login.entity.User;

public interface IUserService {
	
	User registerNewUserAccount(UserDto accountDto)     
		      throws Exception;

	User findByUserName(String username);

	User createUserAccount(UserDto accountDto);

}
