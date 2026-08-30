package com.AnthonySimpson.jobtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Will catch any http error in the app and Allows custom made messages for the errors
@RestControllerAdvice
public class GlobalExceptionHandler {



    /* It handles the general error cases of duplicate emails, user and 
    applications not found, unauthorized access */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalStateException(IllegalStateException ex) {
          return Map.of("error", ex.getMessage());
    }

    /* Colllecting all erros in a Map if anyone tries to create a user that does
    not meet the requirements for the fields given. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put
        (error.getField(), error.getDefaultMessage()));
        return errors;
    }
    
}
