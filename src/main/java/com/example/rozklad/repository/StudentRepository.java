package com.example.rozklad.repository;

import com.example.rozklad.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
