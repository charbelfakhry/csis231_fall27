package com.example.demo.university.service;

import com.example.demo.university.dto.LoginRequest;
import com.example.demo.university.dto.LoginResponse;
import com.example.demo.university.dto.RegistrationRequest;
import com.example.demo.university.dto.RegistrationResponse;
import com.example.demo.university.exception.ConflictException;
import com.example.demo.university.exception.ResourceNotFoundException;
import com.example.demo.university.model.User;
import com.example.demo.university.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository
                .findByUsername(request.username())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Username or Password"));

        if(!user.isActive()){
            throw new ResourceNotFoundException("User is not active");
        }

        boolean validPassword = passwordEncoder.matches(request.password(), user.getPassword());

        if(!validPassword){
            throw new ResourceNotFoundException("Invalid Username or Password");
        }

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name()
        );
    }

    @Transactional
    public RegistrationResponse register(RegistrationRequest request){
        String username = request.username().trim();
        if(userRepository.existsByUsername(username)){
            throw new ConflictException("Username is already taken");
        }

        User user = new User();
        user.setUsername(username);
        // never store the plain-text password
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setFirstName(request.firstName().trim());
        user.setLastName(request.lastName().trim());
        user.setGender(request.gender());
        user.setDateOfBirth(request.dateOfBirth());

        User saved = userRepository.save(user);

        return new RegistrationResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getRole().name()
        );
    }
}
