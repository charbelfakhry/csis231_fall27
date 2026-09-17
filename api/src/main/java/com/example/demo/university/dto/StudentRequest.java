package com.example.demo.university.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record StudentRequest(
        @NotBlank
        String studentNumber,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        String phone,
        LocalDate dateOfBirth,
        @NotBlank
        Long departmentId
) {
}
