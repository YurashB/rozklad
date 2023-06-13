package com.example.rozklad.controller;

import com.example.rozklad.domain.Student;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.StudentRepository;
import com.example.rozklad.service.student.StudentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;
    private final StudentRepository repository;


    public StudentController(StudentService service, StudentRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Optional<Student> searchedEntity = repository.findById(id);

        if (searchedEntity.isPresent()) {
            return new ResponseEntity<>(searchedEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Student> save(@RequestBody Map<String, String> requestParam) {
        try {
            service.add(requestParam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestParam Map<String, String> requestParams) {
        try {
            service.update(id, requestParams);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Student> delete(@RequestParam Long[] ids) {
        try {
            repository.deleteAllById(Arrays.asList(ids));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
