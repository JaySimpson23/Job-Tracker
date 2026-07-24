package com.AnthonySimpson.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AnthonySimpson.jobtracker.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
}
