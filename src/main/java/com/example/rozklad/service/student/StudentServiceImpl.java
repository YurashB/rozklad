package com.example.rozklad.service.student;

import com.example.rozklad.domain.Group;
import com.example.rozklad.domain.Student;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.GroupRepository;
import com.example.rozklad.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void add(Map<String, String> requestParams) {
        String name = requestParams.get("name");
        String email = requestParams.get("email");
        String phone = requestParams.get("phone");
        Group group = groupRepository.findById(Long.parseLong(requestParams.get("group_id"))).get();

        Student student = new Student(name, email, phone, group);
        studentRepository.save(student);
    }

    @Override
    public void update(Long id, Map<String, String> requestParams) {
        Optional<Student> oldStudent = studentRepository.findById(id);

        if (oldStudent.isEmpty()) {
            throw new EntityNotFoundException(Student.class, id);
        }

        String name = requestParams.get("name");
        String email = requestParams.get("email");
        String phone = requestParams.get("phone");
        Group group = groupRepository.findById(Long.parseLong(requestParams.get("group_id"))).get();

        Student updatedStudent = oldStudent.get();
        updatedStudent.setName(name);
        updatedStudent.setEmail(email);
        updatedStudent.setPhone(phone);
        updatedStudent.setGroup(group);
        studentRepository.save(updatedStudent);
    }
}
