package com.example.demo.university.controller;

import com.example.demo.university.dto.LoginRequest;
import com.example.demo.university.dto.LoginResponse;
import com.example.demo.university.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/login")
    public LoginResponse login(
            @Valid
            @RequestBody
            LoginRequest request){
        return authService.login(request);
    }
}

