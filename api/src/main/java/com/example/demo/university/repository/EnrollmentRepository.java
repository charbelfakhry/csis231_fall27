package com.example.demo.university.repository;

import com.example.demo.university.model.Enrollement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollement, Long> {
    List<Enrollement> findByStudentId(Long studentId);
    List<Enrollement> findByCourseId(Long courseId);

}
