package com.example.rozklad.service.department;

import com.example.rozklad.domain.Department;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface DepartmentService {

    void add(Map<String, String> requestParam);

    void update(Long id, Map<String, String> requestParams);
}
