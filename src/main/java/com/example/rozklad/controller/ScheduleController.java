package com.example.rozklad.controller;

import com.example.rozklad.domain.Schedule;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.ScheduleRepository;
import com.example.rozklad.service.schedule.ScheduleService;
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
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;
    private final ScheduleRepository repository;


    public ScheduleController(ScheduleService service, ScheduleRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Schedule> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Schedule> getById(@PathVariable Long id) {
        Optional<Schedule> searchedEntity = repository.findById(id);

        if (searchedEntity.isPresent()) {
            return new ResponseEntity<>(searchedEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Schedule> save(@RequestBody Map<String, String> requestParam) {
        try {
            service.add(requestParam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<Schedule> update(@PathVariable Long id, @RequestParam Map<String, String> requestParams) {
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
    public ResponseEntity<Schedule> delete(@RequestParam Long[] ids) {
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
