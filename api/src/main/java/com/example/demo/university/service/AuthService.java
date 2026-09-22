package com.example.demo.university.service;

import com.example.demo.university.dto.LoginRequest;
import com.example.demo.university.dto.LoginResponse;
import com.example.demo.university.exception.ResourceNotFoundException;
import com.example.demo.university.model.User;
import com.example.demo.university.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request){
        Optional<User> user = Optional.of(userRepository
                .findByUsername(request.username())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Username or Password")))
                ;
        if(!user.get().isActive()){
            throw new ResourceNotFoundException("User is not active");
        }

        boolean validPassword = passwordEncoder.matches(request.password(), user.get().getPassword());

        if(!validPassword){
            throw new ResourceNotFoundException("Invalid Username or Password");
        }

        return new LoginResponse(
                user.get().getId(),
                user.get().getUsername(),
                user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getRole().name()
        );
    }
}
