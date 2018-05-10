package com.mvc.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.mvc.login.dto.UserDto;
import com.mvc.login.entity.User;
import com.mvc.login.service.IUserService;
import com.mvc.login.util.GenericResponse;

@RestController
public class RegistrationController {
	
	@Autowired
	IUserService service;

	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	public GenericResponse registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto, BindingResult result,
			WebRequest request, Errors errors, HttpServletRequest req) {

		User registered = new User();
		if (!result.hasErrors()) {
			registered = service.createUserAccount(accountDto);
		}
		if (result.hasErrors() || registered == null) {
			//return new ModelAndView("registration", "user", accountDto);
			return new GenericResponse(result.getAllErrors(), "error");
		} else {
			//return new ModelAndView("successRegister.html", "user", accountDto);
			return new GenericResponse("success");
		}
		
	}
	
	
}