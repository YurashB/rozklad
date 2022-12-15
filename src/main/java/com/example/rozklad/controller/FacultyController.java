package com.example.rozklad.controller;

import com.example.rozklad.domain.Discipline;
import com.example.rozklad.domain.Faculty;
import com.example.rozklad.domain.Teacher;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.FacultyRepository;
import com.example.rozklad.service.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/faculties")
public class FacultyController {

    private final FacultyService service;
    private final FacultyRepository repository;

    public FacultyController(@Qualifier("facultyServiceImpl") FacultyService service, FacultyRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Faculty> getAll() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Faculty> getById(@PathVariable Long id) {
        Optional<Faculty> searchedEntity = repository.findById(id);

        if (searchedEntity.isPresent()) {
            return new ResponseEntity<>(searchedEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Faculty> save(@RequestBody Map<String, String> requestParam) {
        try {
            service.add(requestParam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<Faculty> update(@PathVariable Long id, @RequestParam Map<String, String> requestParams) {
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
    public ResponseEntity<Faculty> delete(@RequestParam Long[] ids) {
        try {
            repository.deleteAllById(Arrays.asList(ids));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
