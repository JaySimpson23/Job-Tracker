package com.AnthonySimpson.jobtracker.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Application {
    
    // All the data required to make an Application
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String companyName;
    @NotBlank
    String position;
    @NotBlank
    String status;
    @NotNull
    LocalDateTime applied;
    @ManyToOne
    User user;
    @Column(columnDefinition = "TEXT")
    String notes;
}
