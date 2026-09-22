package com.example.demo.university.repository;

import com.example.demo.university.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);

    Optional<User> findByUsername(String username);
}
