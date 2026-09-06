package com.AnthonySimpson.jobtracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.AnthonySimpson.jobtracker.repository.UserRepository;
import com.AnthonySimpson.jobtracker.dto.LoginRequest;
import com.AnthonySimpson.jobtracker.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    /*  All mocks are replications of each dependency that will act according
    to how I set each one */
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    /*Builds a real AuthService but INJECTS the mocks above into it instead
    of real dependencies */
    @InjectMocks
    private AuthService authService;


    /* Communicates to the mocks by saying when this specific method is called
    with the these arguements return a user not found exception */
    @Test
    void login_throwsException_whenUserNotFound() {
        LoginRequest request = new LoginRequest();
        request.setIdentifier("nonexistent@test.com");
        request.setPassword("password123");

        when(userRepository.findByEmailOrUsername("nonexistent@test.com"))
            .thenReturn(Optional.empty());

        // Running the code to make sure the excpetion is thrown.
        assertThrows(IllegalStateException.class, () -> authService.login(request));
    }


    /* Communicates to the mocks by saying when this specific mehtod is called
    with these arguements return a password does not match exception */
    @Test
    void login_throwsException_whenPasswordDoesNotMatch() {
        LoginRequest request = new LoginRequest();
        request.setIdentifier("bacon@test.com");
        request.setPassword("wrongpassword");

        User existingUser = new User();
        existingUser.setEmail("bacon@test.com");
        existingUser.setPassword("hashedPasswordValue");

        when(userRepository.findByEmailOrUsername("bacon@test.com"))
        .thenReturn(Optional.of(existingUser));

        when(passwordEncoder.matches("wrongpassword", "hashedPasswordValue"))
        .thenReturn(false);

        assertThrows(IllegalStateException.class, () -> authService.login(request));

    }

    
    /* Communicates to the mocks by saying when this specific mehtod is called
    with these arguements confirms a Users creditals */
    @Test
    void login_returnsToken_whenCredentialsAreValid() {
        LoginRequest request = new LoginRequest();
        request.setIdentifier("Reema@test.com");
        request.setPassword("Sally");

        User existingUser = new User();
        existingUser.setEmail("Reema@test.com");
        existingUser.setPassword("hashedPasswordValue");

        when(userRepository.findByEmailOrUsername("Reema@test.com"))
        .thenReturn(Optional.of(existingUser));

        when(passwordEncoder.matches("Sally", "hashedPasswordValue"))
        .thenReturn(true);

        when(jwtService.generateToken("Reema@test.com")).thenReturn("fake-jwt-token");

        /* Needs to store the request in a String so assertEquals can compare
         the result of the fake generated token */
        String result = authService.login(request);
        assertEquals("fake-jwt-token", result);
    }
    
}
