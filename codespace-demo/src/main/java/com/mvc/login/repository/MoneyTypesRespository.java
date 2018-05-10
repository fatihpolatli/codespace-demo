package com.mvc.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.login.entity.MoneyTypes;

public interface MoneyTypesRespository extends JpaRepository<MoneyTypes, Long> {
	 
   
}
