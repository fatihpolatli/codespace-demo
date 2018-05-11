package com.mvc.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.dao.IMoneyTypesDao;
import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.service.IMoneyTypesService;

@Component
public class MoneyTypesService implements IMoneyTypesService{
	
	@Autowired
	IMoneyTypesDao moneyTypesDao;
	
	@Override
	public List<MoneyTypes> getMoneyTypes(){
		
		return moneyTypesDao.getMoneyTypes();
	}

	@Override
	public MoneyTypes save(MoneyTypes moneyType) {
		return moneyTypesDao.save(moneyType);
		
	}

}
