package com.AnthonySimpson.jobtracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnthonySimpson.jobtracker.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Where the users re stored
    // Optional made so they can be found by their Email/Username
    @Query("Select u from User u Where u.email = :identifier OR u.username = :identifier")
    Optional<User> findByEmailOrUsername(@Param("identifier") String identifier);
    
}
