package com.AnthonySimpson.jobtracker.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
  
    // All the data that will be required to make a user
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
