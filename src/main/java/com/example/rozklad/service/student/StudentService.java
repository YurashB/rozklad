package com.example.rozklad.service.student;

import java.util.Map;

public interface StudentService {
    void add(Map<String, String> requestParams);

    void update(Long id, Map<String, String> requestParams);
}
