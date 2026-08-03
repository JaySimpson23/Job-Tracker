package com.AnthonySimpson.jobtracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.AnthonySimpson.jobtracker.service.ApplicationService;
import com.AnthonySimpson.jobtracker.domain.User;
import com.AnthonySimpson.jobtracker.domain.Application;
import com.AnthonySimpson.jobtracker.dto.ApplicationResponse;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

   private final ApplicationService applicationService;

   // Creating the application
   @PostMapping
   public ApplicationResponse create(@RequestBody Application application, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    Application saved = applicationService.create(application, user);
    return ApplicationResponse.fromApplication(saved);
   }

   //Retireving all applicaitons for the user
   @GetMapping
     public List<ApplicationResponse> getAllForUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Application> applications = applicationService.getAllForUser(user);
       return  applications.stream().map(ApplicationResponse::fromApplication).toList();
    }

    // Retrieving one application for the user
    @GetMapping("/{id}")
    public ApplicationResponse getById(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ApplicationResponse.fromApplication(applicationService.getById(id, user));
    }

    // Updating the application
    @PutMapping("/{id}")
    public ApplicationResponse update(@PathVariable Long id, @RequestBody Application application, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ApplicationResponse.fromApplication(applicationService.update(id, application, user));
    }

    // Deleting the application
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        applicationService.delete(id, user);
    }  


 }
