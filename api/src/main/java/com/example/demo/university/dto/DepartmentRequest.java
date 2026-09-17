package com.example.demo.university.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartmentRequest(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
