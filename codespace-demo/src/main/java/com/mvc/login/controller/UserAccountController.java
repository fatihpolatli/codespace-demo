package com.mvc.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.login.dto.BalanceDto;
import com.mvc.login.dto.TransferDto;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.service.IMoneyTypesService;
import com.mvc.login.service.IUserService;
import com.mvc.login.util.GenericResponse;

@RestController
@RequestMapping(path = "/restful/accounts")
public class UserAccountController {

	@Autowired
	IMoneyTypesService moneyTypesService;

	@Autowired
	IUserService userService;

	@PostMapping(value = "/add")
	public GenericResponse addUserAccount(@RequestBody UserAccount account) {

		try {
			userService.addAcount(account);
			return new GenericResponse(null, null, true);
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}
	
	@PostMapping(value = "/delete")
	public GenericResponse deleteUserAccount(@RequestBody UserAccount account) {

		try {
			userService.deleteAccount(account);
			return new GenericResponse(null, null, true);
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}

	@GetMapping(value = "/list")
	public GenericResponse getUserAccounts() {

		System.out.println("NAME");
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

		try {

			return new GenericResponse(null, null, userService.getAccounts());
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}

	@PostMapping(value = "/balance")
	public GenericResponse addBalance(@RequestBody BalanceDto balance) {

		try {

			return new GenericResponse(null, null, userService.addSubtractBalance(balance));
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}
	
	@PostMapping(value = "/currentBalance")
	public GenericResponse getCurrentBalance(@RequestBody BalanceDto balance) {

		try {

			return new GenericResponse(null, null, userService.getCurrentBalance(balance));
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}
	
	@PostMapping(value = "/history")
	public GenericResponse getAccountHistory(@RequestBody BalanceDto balance) {

		try {

			return new GenericResponse(null, null, userService.getAccountHistory(balance));
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}
	
	@PostMapping(value = "/transfer")
	public GenericResponse transferMoney(@RequestBody TransferDto transferData) {

		try {

			return new GenericResponse(null, null, userService.transferMoney(transferData));
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}
	
	@PostMapping(value = "/loadBalance")
	public GenericResponse loadBalance(@RequestBody BalanceDto balance) {

		try {

			return new GenericResponse(null, null, userService.loadBalance(balance));
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}

}