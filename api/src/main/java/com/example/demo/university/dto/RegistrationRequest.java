package com.example.demo.university.dto;

import com.example.demo.university.model.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegistrationRequest(
        @NotBlank
        @Size(min = 3, max = 50)
        String username,
        @NotBlank
        @Size(min = 4)
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotNull
        Gender gender,
        @NotNull
        @Past
        LocalDate dateOfBirth
) {
}
