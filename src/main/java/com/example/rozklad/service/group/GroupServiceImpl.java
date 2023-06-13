package com.example.rozklad.service.group;

import com.example.rozklad.domain.Department;
import com.example.rozklad.domain.Faculty;
import com.example.rozklad.domain.Group;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.DepartmentRepository;
import com.example.rozklad.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;
    private final DepartmentRepository departmentRepository;

    public GroupServiceImpl(GroupRepository groupRepository, DepartmentRepository departmentRepository) {
        this.groupRepository = groupRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void add(Map<String, String> requestParams) {
        String name = requestParams.get("name");
        int course = Integer.parseInt(requestParams.get("course"));
        Department department =  departmentRepository.findById(Long.parseLong(requestParams.get("department_id"))).get();

        Group group = new Group(name, course, department);
        groupRepository.save(group);
    }

    @Override
    public void update(Long id, Map<String, String> requestParams) {
        Optional<Group> oldGroup = groupRepository.findById(id);

        if (oldGroup.isEmpty()) {
            throw new EntityNotFoundException(Group.class, id);
        }

        String name = requestParams.get("name");
        int course = Integer.parseInt(requestParams.get("course"));
        Department department =  departmentRepository.findById(Long.parseLong(requestParams.get("department_id"))).get();

        Group updatedGroup = oldGroup.get();
        updatedGroup.setName(name);
        updatedGroup.setCourse(course);
        updatedGroup.setDepartment(department);
        groupRepository.save(updatedGroup);
    }
}
