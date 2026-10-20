package org.example.csis231_fall27_2.api;

public record RegistrationResponse(
        Long id,
        String username,
        String firstName,
        String lastName,
        String role
        ) {
}
