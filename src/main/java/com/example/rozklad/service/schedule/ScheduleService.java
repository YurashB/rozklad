package com.example.rozklad.service.schedule;

import java.util.Map;

public interface ScheduleService {
    void add(Map<String, String> teacherParam);

    void update(Long id, Map<String, String> teacherParams);
}

