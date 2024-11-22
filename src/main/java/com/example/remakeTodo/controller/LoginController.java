package com.example.remakeTodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
    public String signup() {
    	return "signup";
    }
	
	@GetMapping("/resetpassword")
	public String reset() {
		return "reset";
	}

}
