package com.example.rozklad.service.department;

import com.example.rozklad.domain.Department;
import com.example.rozklad.domain.Faculty;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Map<String, String> requestParams) {
        String name = requestParams.get("name");
        String shortName = requestParams.get("shortName");
        Long facultyId = Long.parseLong(requestParams.get("faculty_id"));

        Department department = new Department(name, shortName, facultyId);
        repository.save(department);
    }

    @Override
    public void update(Long id, Map<String, String> requestParams) {
        Optional<Department> oldDepartment = repository.findById(id);

        if (oldDepartment.isEmpty()) {
            throw new EntityNotFoundException(Faculty.class, id);
        }

        String name = requestParams.get("name");
        String shortName = requestParams.get("shortName");
        Long facultyId = Long.parseLong(requestParams.get("faculty_id"));

        Department updatedDepartment = oldDepartment.get();
        updatedDepartment.setName(name);
        updatedDepartment.setShortName(shortName);
        updatedDepartment.setFacultyId(facultyId);
        repository.save(updatedDepartment);
    }
}
