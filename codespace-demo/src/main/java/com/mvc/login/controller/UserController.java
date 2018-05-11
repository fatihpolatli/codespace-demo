package com.mvc.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.login.service.IUserService;
import com.mvc.login.util.GenericResponse;

@RestController
@RequestMapping(path = "/restful/user")
public class UserController {


	@Autowired
	IUserService userService;

	@GetMapping(value = "/list")
	public GenericResponse getUserList() {

		try {
			
			return new GenericResponse(null, null, userService.getUserList());
		} catch (Exception e) {
			return new GenericResponse(e.getMessage(), "error");
		}

	}


}