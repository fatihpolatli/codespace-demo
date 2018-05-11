package com.mvc.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.login.entity.User;
import com.mvc.login.entity.UserWithoutPassword;

public interface UserWithoutPasswordRepository extends JpaRepository<UserWithoutPassword, Long> {
	 
    User findByUsername(String username);

	User findByEmail(String email);
}
