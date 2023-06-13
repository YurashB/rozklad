package com.example.rozklad.service.department;

import com.example.rozklad.domain.Department;
import com.example.rozklad.domain.Faculty;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.DepartmentRepository;
import com.example.rozklad.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    public DepartmentServiceImpl(DepartmentRepository repository, FacultyRepository facultyRepository) {
        this.departmentRepository = repository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void add(Map<String, String> requestParams) {
        String name = requestParams.get("name");
        String shortName = requestParams.get("short_name");
        Faculty faculty = facultyRepository.findById(Long.parseLong(requestParams.get("faculty_id"))).get();

        Department department = new Department(name, shortName, faculty);
        departmentRepository.save(department);
    }

    @Override
    public void update(Long id, Map<String, String> requestParams) {
        Optional<Department> oldDepartment = departmentRepository.findById(id);

        if (oldDepartment.isEmpty()) {
            throw new EntityNotFoundException(Faculty.class, id);
        }

        String name = requestParams.get("name");
        String shortName = requestParams.get("short_name");
        Faculty faculty = facultyRepository.findById(Long.parseLong(requestParams.get("faculty_id"))).get();

        Department updatedDepartment = oldDepartment.get();
        updatedDepartment.setName(name);
        updatedDepartment.setShortName(shortName);
        updatedDepartment.setFaculty(faculty);
        departmentRepository.save(updatedDepartment);
    }
}
