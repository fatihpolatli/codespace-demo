package com.mvc.login.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.service.IMoneyTypesService;

@Component
public class DataInitialize {
	
	@Autowired
	IMoneyTypesService service;

	@PostConstruct
	private void init() {
		
		MoneyTypes t = new MoneyTypes();
		t.setId(1L);
		t.setMoneyLabel("TRY");
		
		MoneyTypes g = new MoneyTypes();
		g.setId(2L);
		g.setMoneyLabel("GBP");
		
		service.save(t);
		service.save(g);
	}
}
