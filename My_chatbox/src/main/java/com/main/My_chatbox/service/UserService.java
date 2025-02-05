package com.main.My_chatbox.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.main.My_chatbox.entity.User;
import com.main.My_chatbox.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
		userRepository.save(user);
	}

}
