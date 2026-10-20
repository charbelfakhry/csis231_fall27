package com.example.demo.university.dto;

public record RegistrationResponse(Long id,
                                   String username,
                                   String firstName,
                                   String lastName,
                                   String role) {

}
