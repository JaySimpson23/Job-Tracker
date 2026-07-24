package com.AnthonySimpson.jobtracker.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AnthonySimpson.jobtracker.domain.User;
import com.AnthonySimpson.jobtracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public User register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            throw new IllegalStateException("Email already in use");
       }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
