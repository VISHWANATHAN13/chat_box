package com.main.My_chatbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.My_chatbox.DTO.UserDTO;
import com.main.My_chatbox.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/save")
	public String saveUser(@RequestBody UserDTO userDTO) {
		String id = userService.addUser(userDTO);
		return id;
	}

}
