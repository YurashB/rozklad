package com.example.rozklad.service.teacher;

import com.example.rozklad.domain.Teacher;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void updateTeacher(Long id, Map<String, String> teacherParam) {
        Optional<Teacher> oldTeacher = teacherRepository.findById(id);

        if (oldTeacher.isEmpty()) {
            throw new EntityNotFoundException(new Teacher(), id);
        }

        String name = teacherParam.get("name");
        String surname = teacherParam.get("surname");
        String email = teacherParam.get("email");
        String phone = teacherParam.get("phone");

        Teacher updatedTeacher = oldTeacher.get();
        updatedTeacher.setName(name);
        updatedTeacher.setPhone(surname);
        updatedTeacher.setEmail(email);
        updatedTeacher.setSurname(phone);
        teacherRepository.save(updatedTeacher);
    }

    @Override
    public void addTeacher(Map<String, String> teacherParam) {
        String name = teacherParam.get("name");
        String surname = teacherParam.get("surname");
        String email = teacherParam.get("email");
        String phone = teacherParam.get("phone");

        Teacher newTeacher = new Teacher(name, surname, email, phone);
        teacherRepository.save(newTeacher);
    }
}
