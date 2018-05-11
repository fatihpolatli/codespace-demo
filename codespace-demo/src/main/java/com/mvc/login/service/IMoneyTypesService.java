package com.mvc.login.service;

import java.util.List;

import com.mvc.login.entity.MoneyTypes;

public interface IMoneyTypesService {

	List<MoneyTypes> getMoneyTypes();

	MoneyTypes save(MoneyTypes moneyType);

}
