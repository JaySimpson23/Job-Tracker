package com.AnthonySimpson.jobtracker.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AnthonySimpson.jobtracker.domain.User;
import com.AnthonySimpson.jobtracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // Cheking to see if the user already exist via their email or their username
    // if so, will return Email/username already in use
    public User register(User user) {
        Optional<User> existingUser = userRepository.findByEmailOrUsername(user.getEmail());
        if(existingUser.isPresent()) {
            throw new IllegalStateException("Email already in use");
       }

       Optional<User> existingUsername = userRepository.findByEmailOrUsername(user.getUsername());
       if(existingUsername.isPresent()) {
        throw new IllegalStateException("Username already in use");
       }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
