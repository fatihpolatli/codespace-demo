package com.mvc.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.login.entity.UserAccount;

public interface UserAccountRespository extends JpaRepository<UserAccount, Long> {

	UserAccount findByMoneyTypeId(Long accountTypeId);
	 
   
}