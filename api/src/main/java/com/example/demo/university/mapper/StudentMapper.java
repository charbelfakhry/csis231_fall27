package com.example.demo.university.mapper;

import com.example.demo.university.dto.StudentRequest;
import com.example.demo.university.dto.StudentResponse;
import com.example.demo.university.model.Department;
import com.example.demo.university.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(
            StudentRequest request,
            Department department){

        Student student = new Student();
        student.setStudentNumber(request.studentNumber());
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setPhone(request.phone());
        student.setDateOfBirth(request.dateOfBirth());
        student.setDepartment(department);
        return student;
    }

    public StudentResponse toResponse(Student student){
        Department department = student.getDepartment();
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getDateOfBirth(),
                student.getEnrollmentDate(),
                student.getStatus(),
                department != null ? department.getId() : null,
                department != null ? department.getName() : null
        );
    }

}
