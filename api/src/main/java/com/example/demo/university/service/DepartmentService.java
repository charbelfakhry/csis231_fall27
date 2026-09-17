package com.example.demo.university.service;

import com.example.demo.university.dto.DepartmentRequest;
import com.example.demo.university.dto.DepartmentResponse;
import com.example.demo.university.exception.ResourceNotFoundException;
import com.example.demo.university.mapper.DepartmentMapper;
import com.example.demo.university.model.Department;
import com.example.demo.university.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public DepartmentService(DepartmentRepository repository,
                             DepartmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DepartmentResponse create(DepartmentRequest request){
        Department department = mapper.toEntity(request);
        return mapper.toResponse(repository.save(department));
    }

    public List<DepartmentResponse> findAll(){

        /*List<DepartmentResponse> responsesToReturn = new ArrayList<>();
        List<Department> deps = repository.findAll();
        for(Department dep : deps){
            responsesToReturn.add(mapper.toResponse(dep));
        }

        return responsesToReturn;*/

        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public DepartmentResponse findById(Long id) {
        Department department = findEntity(id);
        return mapper.toResponse(department);
    }

    public void delete(Long id) {
        Department department = findEntity(id);
        repository.delete(department);
    }

    /**
     * Helpers
     * @param id
     * @return
     */
    public Department findEntity(Long id) {
        return
                repository.findById(id).
                        orElseThrow(
                                () -> new ResourceNotFoundException("Department not found "+id));
    }

}
