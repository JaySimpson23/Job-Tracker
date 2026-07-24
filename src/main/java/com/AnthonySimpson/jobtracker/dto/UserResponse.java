package com.AnthonySimpson.jobtracker.dto;

import java.time.LocalDateTime;

import com.AnthonySimpson.jobtracker.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    Long id;
    String username;
    String email;
    LocalDateTime createdAt;

    public static UserResponse fromUser(User user) {
        UserResponse response = new UserResponse();

        response.id = user.getId();
        response.username = user.getUsername();
        response.email = user.getEmail();
        response.createdAt = user.getCreatedAt();

        return response;
    }
    
}
