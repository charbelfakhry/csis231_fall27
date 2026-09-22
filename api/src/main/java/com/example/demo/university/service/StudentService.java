package com.example.demo.university.service;

import com.example.demo.university.dto.StudentRequest;
import com.example.demo.university.dto.StudentResponse;
import com.example.demo.university.exception.ResourceNotFoundException;
import com.example.demo.university.mapper.StudentMapper;
import com.example.demo.university.model.Department;
import com.example.demo.university.model.Student;
import com.example.demo.university.repository.DepartmentRepository;
import com.example.demo.university.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final DepartmentRepository departmentRepository;
    private final StudentMapper mapper;


    public StudentService(StudentRepository repository, DepartmentRepository departmentRepository, StudentMapper mapper) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Transactional
    public StudentResponse create(StudentRequest request){
        Department department = departmentRepository
                .findById(request.departmentId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Department not found")
                );
        Student student = mapper.toEntity(request, department);
        return mapper.toResponse(repository.save(student));
    }

    @Transactional
    public List<StudentResponse> findAll(){
        return repository
                .findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public StudentResponse findById(Long id){
        return mapper.toResponse(findEntity(id));
    }

    @Transactional
    public StudentResponse update(Long id, StudentRequest request){
        Student student = findEntity(id);
        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Department not found")
                );
        mapper.updateEntity(student, request, department);
        Student updatedStudent = repository.save(student);

        return mapper.toResponse(updatedStudent);
    }

    @Transactional
    public void delete(Long id){
        Student student = findEntity(id);
        repository.delete(student);
    }


    /**
     * Helper methods
     */

    private Student findEntity(Long id){
        return repository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Student not found")
                );
    }
}
