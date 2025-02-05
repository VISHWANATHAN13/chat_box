package com.main.My_chatbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.My_chatbox.entity.User;
import com.main.My_chatbox.service.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {

	private final UserService userService;

	public SignupController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String showSignupForm() {
		return "signup"; // Thymeleaf template for signup page
	}

	@PostMapping
	public String registerUser(User user) {
		userService.registerUser(user);
		return "redirect:/login"; // Redirect to login page after registration
	}
}
