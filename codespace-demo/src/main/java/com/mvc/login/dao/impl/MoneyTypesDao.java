package com.mvc.login.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IMoneyTypesDao;
import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.exception.NoCurrencyTypeException;
import com.mvc.login.repository.MoneyTypesRespository;

@Component
public class MoneyTypesDao implements IMoneyTypesDao{
	
	@Autowired
	MoneyTypesRespository repository;

	@Override
	public List<MoneyTypes> getMoneyTypes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public MoneyTypes save(MoneyTypes moneyType) {
		return repository.save(moneyType);
		
	}
	
	@Override
	public MoneyTypes findById(Long id) throws NoCurrencyTypeException {
		
		MoneyTypes result = repository.findById(id).get();
		
		if(result == null) {
			
			throw new NoCurrencyTypeException();
		}
		
		return result;
		
	}

}
