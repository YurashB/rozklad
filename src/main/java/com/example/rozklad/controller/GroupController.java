package com.example.rozklad.controller;

import com.example.rozklad.domain.Group;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.GroupRepository;
import com.example.rozklad.service.group.GroupService;
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
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService service;
    private final GroupRepository repository;

    public GroupController(GroupService service, GroupRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Group> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Group> getById(@PathVariable Long id) {
        Optional<Group> searchedEntity = repository.findById(id);

        if (searchedEntity.isPresent()) {
            return new ResponseEntity<>(searchedEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Group> save(@RequestBody Map<String, String> requestParam) {
        try {
            service.add(requestParam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<Group> update(@PathVariable Long id, @RequestParam Map<String, String> requestParams) {
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
    public ResponseEntity<Group> delete(@RequestParam Long[] ids) {
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
