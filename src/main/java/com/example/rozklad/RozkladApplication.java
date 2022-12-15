package com.example.rozklad;

import com.example.rozklad.domain.Faculty;
import com.example.rozklad.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RozkladApplication {

    public static void main(String[] args) {
        SpringApplication.run(RozkladApplication.class, args);
    }
}
