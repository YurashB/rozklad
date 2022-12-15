package com.example.rozklad.service.teacher;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface TeacherService {
    void addTeacher(Map<String, String> teacherParam);

    void updateTeacher(Long id, Map<String, String> teacherParams);
}
