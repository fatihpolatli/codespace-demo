package com.mvc.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.service.IMoneyTypesService;
import com.mvc.login.service.IUserService;
import com.mvc.login.util.GenericResponse;

@RestController
@RequestMapping(path = "/restful/currency")
public class MoneyTypesController {

	@Autowired
	IMoneyTypesService moneyTypesService;
	
	@Autowired
	IUserService userService;

	@GetMapping(value = "/types")
	public GenericResponse getAccountTypes() {
		
		System.out.println("NAME");
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

		try {
			return new GenericResponse(null, null, moneyTypesService.getMoneyTypes());
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}

	@PostMapping(value = "/add")
	public GenericResponse addMoneyType(@RequestBody MoneyTypes moneyType) {

		try {
			moneyTypesService.save(moneyType);
			return new GenericResponse(null, null, true);
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}
		
	}
	


}