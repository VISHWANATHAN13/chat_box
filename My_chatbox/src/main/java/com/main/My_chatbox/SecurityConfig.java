package com.main.My_chatbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/signup", "/register", "/css/**", "/js/**").permitAll() // Allow access to signup and static resources
                .anyRequest().authenticated() // Require authentication for all other requests
            )
            .formLogin(form -> form
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/home", true) // Redirect after successful login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") // Redirect after logout
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }
	
}
