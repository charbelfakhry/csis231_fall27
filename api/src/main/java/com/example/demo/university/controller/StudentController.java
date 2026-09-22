package com.example.demo.university.controller;

import com.example.demo.university.dto.StudentRequest;
import com.example.demo.university.dto.StudentResponse;
import com.example.demo.university.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(
            @Valid
            @RequestBody
            StudentRequest request
    ){
        return studentService.create(request);
    }

    @GetMapping
    public List<StudentResponse> getAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(Long id){
        return studentService.findById(id);
    }

    @PutMapping("/{id}")
    public StudentResponse update(Long id,
                                  @Valid
                                  @RequestBody
                                  StudentRequest request){
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id){
        studentService.delete(id);
    }

}
