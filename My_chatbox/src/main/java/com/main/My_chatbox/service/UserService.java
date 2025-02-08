package com.main.My_chatbox.service;

import org.springframework.stereotype.Service;

import com.main.My_chatbox.DTO.UserDTO;

@Service
public interface UserService {

	String addUser(UserDTO userDTO);
	
	

}
