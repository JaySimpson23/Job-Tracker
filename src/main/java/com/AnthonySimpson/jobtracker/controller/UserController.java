package com.AnthonySimpson.jobtracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AnthonySimpson.jobtracker.domain.User;
import com.AnthonySimpson.jobtracker.dto.UserResponse;
import com.AnthonySimpson.jobtracker.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {
        User savedUser = userService.register(user);
        return UserResponse.fromUser(savedUser);
    }
    
}
