package org.example.csis231_fall27_2.api;

public record LoginResponse(
        Long id,
        String username,
        String firstName,
        String lastName,
        String role
        ) {
}
