package org.example.csis231_fall27_2.api;

import java.time.LocalDate;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDate dateOfBirth,
        LocalDate enrollmentDate,
        String status,
        Long departmentId,
        String departmentName
) {
}
