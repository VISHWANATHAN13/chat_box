package com.main.My_chatbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.main.My_chatbox.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
}