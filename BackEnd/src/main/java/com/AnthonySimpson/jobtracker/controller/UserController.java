package com.AnthonySimpson.jobtracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

import com.AnthonySimpson.jobtracker.domain.User;
import com.AnthonySimpson.jobtracker.dto.UserResponse;
import com.AnthonySimpson.jobtracker.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Registering an account
    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {
        User savedUser = userService.register(user);
        return UserResponse.fromUser(savedUser);
    }

    // getting the registered user
    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return UserResponse.fromUser(user);
    }
    
}
