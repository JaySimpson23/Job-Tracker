package com.AnthonySimpson.jobtracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    
    String identifier;
    String password;
}
