package com.example.rozklad.controller;

import com.example.rozklad.domain.Discipline;
import com.example.rozklad.domain.Teacher;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.TeacherRepository;
import com.example.rozklad.service.teacher.TeacherServiceImpl;
import com.example.rozklad.service.teacher.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherServiceImpl teacherService, TeacherRepository teacherRepository) {
        this.teacherService = teacherService;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> searchedTeacher = teacherRepository.findById(id);

        if (searchedTeacher.isPresent()) {
            return new ResponseEntity<>(searchedTeacher.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Map<String, String> requestTeacherParam) {
        try {
            teacherService.addTeacher(requestTeacherParam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<Teacher> updateDiscipline(@PathVariable Long id, @RequestParam Map<String, String> requestTeacherParams) {
        try {
            System.out.println(requestTeacherParams.toString() +  "  " +  id);
            teacherService.updateTeacher(id, requestTeacherParams);

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
    public ResponseEntity<Discipline> deleteDisciplines(@RequestParam Long[] ids) {
        try {
            teacherRepository.deleteAllById(Arrays.asList(ids));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
