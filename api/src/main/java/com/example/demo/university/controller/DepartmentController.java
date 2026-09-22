package com.example.demo.university.controller;

import com.example.demo.university.dto.DepartmentRequest;
import com.example.demo.university.dto.DepartmentResponse;
import com.example.demo.university.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse create(
            @Valid
            @RequestBody
            DepartmentRequest request
    ){
        return departmentService.create(request);
    }

    @GetMapping
    public List<DepartmentResponse> findAll(){
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public DepartmentResponse findById(Long id){
        return departmentService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id){
        departmentService.delete(id);
    }
}
