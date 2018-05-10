package com.mvc.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restful")
public class TestController {

	@GetMapping(path="test")
	public String test() {
		
		return "this is test";
	}
	
	@GetMapping(path="welcome")
	public String welcome() {
		
		return "welcome home";
	}
}
