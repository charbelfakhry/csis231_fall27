package com.example.demo.university.controller;

import com.example.demo.university.dto.LoginRequest;
import com.example.demo.university.dto.LoginResponse;
import com.example.demo.university.dto.RegistrationRequest;
import com.example.demo.university.dto.RegistrationResponse;
import com.example.demo.university.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid
            @RequestBody
            LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponse register(
            @Valid
            @RequestBody
            RegistrationRequest request
    ){
        return authService.register(request);
    }
}
