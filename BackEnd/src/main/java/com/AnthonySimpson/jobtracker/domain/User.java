package com.AnthonySimpson.jobtracker.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    String username;
    @NotBlank
    @Size(min = 6)
    String password;
    @NotBlank
    @Email
    @Column(unique = true)
    String email;
    LocalDateTime createdAt;
    int applicationCount = 0;
}
