package com.AnthonySimpson.jobtracker.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {
  
    @GeneratedValue
    @Id
    Long id;
    @Column(unique = true)
    String username;
    String password;
    @Column(unique = true)
    String email;
    LocalDateTime createdAt;
}
