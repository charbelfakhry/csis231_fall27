package com.example.demo.university.dto;

import com.example.demo.university.model.StudentStatus;

import java.time.LocalDate;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDate dateOfBirth,
        LocalDate enrollmentDate,
        StudentStatus status,
        Long departmentId,
        String departmentName
) {
}
