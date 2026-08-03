package com.AnthonySimpson.jobtracker.dto;

import java.time.LocalDateTime;

import com.AnthonySimpson.jobtracker.domain.Application;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ApplicationResponse {

    Long id;
    String companyName;
    String position;
    String status;
    String notes;
    LocalDateTime applied;

    public static ApplicationResponse fromApplication(Application application) {
        ApplicationResponse response = new ApplicationResponse();

        response.id = application.getId();
        response.companyName = application.getCompanyName();
        response.position = application.getPosition();
        response.status = application.getStatus();
        response.notes = application.getNotes();
        response.applied = application.getApplied();

        return response;
    }
    
}
