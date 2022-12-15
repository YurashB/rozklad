package com.example.rozklad.service.faculty;

import java.util.Map;

public interface FacultyService {

    void add(Map<String, String> requestParams);

    void update(Long id, Map<String, String> requestParams);
}
