package com.example.demo.university.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "enrollments",
uniqueConstraints = {
    @UniqueConstraint(
            columnNames =
                    {"student_id", "course_id", "semster", "academic_year"}
    )})
@Getter
@Setter
public class Enrollement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDate enrollmentDate = LocalDate.now();

    @Column(nullable = false)
    private String semster;

    @Column(nullable = false)
    private String academicYear;

    @Column(precision = 5, scale = 2)
    private BigDecimal grade;

    private EnrollmentStatus status = EnrollmentStatus.ENROLLED;

}
