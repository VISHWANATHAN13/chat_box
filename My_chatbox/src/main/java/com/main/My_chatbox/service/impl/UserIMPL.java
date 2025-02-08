package com.main.My_chatbox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.My_chatbox.DTO.UserDTO;
import com.main.My_chatbox.entity.User;
import com.main.My_chatbox.repository.UserRepository;
import com.main.My_chatbox.service.UserService;

@Service
public class UserIMPL implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String addUser(UserDTO userDTO) {

		User user = new User(userDTO.getUserid(), userDTO.getUsername(), userDTO.getEmail(),

				this.passwordEncoder.encode(userDTO.getPassword())

		);
		userRepository.save(user);

		return user.getUsername();
	}

}
