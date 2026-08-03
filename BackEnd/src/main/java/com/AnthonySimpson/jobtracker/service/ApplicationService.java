package com.AnthonySimpson.jobtracker.service;

import org.springframework.stereotype.Service;

import com.AnthonySimpson.jobtracker.domain.Application;
import com.AnthonySimpson.jobtracker.domain.User;
import com.AnthonySimpson.jobtracker.repository.ApplicationRepository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {

     private final ApplicationRepository applicationRepository;


     // creates an application for the current user and sets the user to that application.
    public Application create(Application application, User currentUser) {
        application.setUser(currentUser);
        return applicationRepository.save(application);
    
    }
    // Gets all the data for the current user.
    public List<Application> getAllForUser(User currentUser) {
        return applicationRepository.findByUser(currentUser);
    }
    
    /* If you cant find the application by name you have the option to 
    find it by the id that is connected to the user. Also if there is no application
    in the database it will send a Application not found.
    Same thing goes for if you can find the user conccected to that application
    it will send user not found */
     public Application getById(Long id, User currentUser) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if(!applicationOptional.isPresent()) {
            throw new IllegalStateException("Application not found");
        }
        Application application = applicationOptional.get();
        if(!application.getUser().equals(currentUser)) {
            throw new IllegalStateException("User not found");
        }
        return application;
    }

    /* Same Look up and check from previous method but sets the company and detail
    and updates the application and then saves the updates to the repo. */
    public Application update(Long id, Application updatedData, User currentUser) {
         Optional<Application> applicationOptional = applicationRepository.findById(id);
        if(!applicationOptional.isPresent()) {
            throw new IllegalStateException("Application not found");
        }
        Application application = applicationOptional.get();
        if(!application.getUser().equals(currentUser)) {
            throw new IllegalStateException("User not found");
        }
        application.setCompanyName(updatedData.getCompanyName());
        application.setPosition(updatedData.getPosition());
        application.setStatus(updatedData.getStatus());
        application.setApplied(updatedData.getApplied());
        application.setNotes(updatedData.getNotes());
        return applicationRepository.save(application);
    }

    //Deleting the application
    public void delete(Long id, User currentUser) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if(!applicationOptional.isPresent()) {
            throw new IllegalStateException("Application not found");
        }
        Application application = applicationOptional.get();
        if(!application.getUser().equals(currentUser)) {
            throw new IllegalStateException("User not found");
        }
        applicationRepository.delete(application);
    }
}