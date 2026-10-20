package org.example.csis231_fall27_2.api;

import java.time.LocalDate;

public record RegistrationRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String gender,
        LocalDate dateOfBirth
        ) {
}
