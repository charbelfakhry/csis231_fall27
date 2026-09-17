package com.example.demo.university.mapper;

import com.example.demo.university.dto.DepartmentRequest;
import com.example.demo.university.dto.DepartmentResponse;
import com.example.demo.university.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department toEntity(DepartmentRequest request){
        Department department = new Department();
        department.setName(request.name());
        department.setDescription(request.description());
        return department;
    }

    public DepartmentResponse toResponse(Department department){
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
}
