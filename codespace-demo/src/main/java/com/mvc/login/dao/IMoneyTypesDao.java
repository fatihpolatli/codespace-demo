package com.mvc.login.dao;

import java.util.List;

import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.exception.NoCurrencyTypeException;

public interface IMoneyTypesDao {
	
	List<MoneyTypes> getMoneyTypes();

	MoneyTypes save(MoneyTypes moneyType);

	MoneyTypes findById(Long id) throws NoCurrencyTypeException;

}
