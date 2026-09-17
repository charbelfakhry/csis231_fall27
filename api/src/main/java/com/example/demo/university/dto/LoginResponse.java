package com.example.demo.university.dto;

public record LoginResponse(Long id,
                            String username,
                            String firstName,
                            String lastName,
                            String role) {

}
