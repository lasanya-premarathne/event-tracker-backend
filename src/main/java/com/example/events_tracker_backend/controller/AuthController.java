package com.example.events_tracker_backend.controller;

import com.example.events_tracker_backend.dto.LoginRequest;
import com.example.events_tracker_backend.dto.LoginResponse;
import com.example.events_tracker_backend.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest.getUserName(), loginRequest.getPassword());
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

    }
}
