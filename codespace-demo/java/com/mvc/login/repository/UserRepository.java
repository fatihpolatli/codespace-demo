package com.mvc.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.login.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 
    User findByUsername(String username);

	User findByEmail(String email);
}
