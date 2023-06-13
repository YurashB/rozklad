package com.example.rozklad.service.group;

import java.util.Map;

public interface GroupService {
    void add(Map<String, String> requestParams);

    void update(Long id, Map<String, String> requestParams);
}
