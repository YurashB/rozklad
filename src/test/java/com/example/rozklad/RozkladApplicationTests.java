package com.example.rozklad;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RozkladApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    public void deleteTestDiscipline() {

    }
}
