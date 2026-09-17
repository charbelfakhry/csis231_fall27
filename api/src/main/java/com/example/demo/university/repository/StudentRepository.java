package com.example.demo.university.repository;

import com.example.demo.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudnentNumber(String studentNumber);
    boolean existsByEmail(String email);
}
