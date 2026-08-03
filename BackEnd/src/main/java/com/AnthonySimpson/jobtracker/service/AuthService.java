package com.AnthonySimpson.jobtracker.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AnthonySimpson.jobtracker.dto.LoginRequest;
import com.AnthonySimpson.jobtracker.repository.UserRepository;
import com.AnthonySimpson.jobtracker.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    // Looking for the user by their username or email
    // if either one does not exist will display invalid credentials until the correct email/username is enterned
    public String login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmailOrUsername(request.getIdentifier());
        if(!userOptional.isPresent()) {
            throw new IllegalStateException("Invalid Credentials");
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Invalid Credentials");
        }
        return jwtService.generateToken(user.getEmail());
    }
}
