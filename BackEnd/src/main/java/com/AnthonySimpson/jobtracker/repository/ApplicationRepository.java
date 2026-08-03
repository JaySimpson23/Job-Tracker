package com.AnthonySimpson.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AnthonySimpson.jobtracker.domain.Application;
import com.AnthonySimpson.jobtracker.domain.User;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Where all the applications will be stroed
     List<Application> findByUser(User user);
}
