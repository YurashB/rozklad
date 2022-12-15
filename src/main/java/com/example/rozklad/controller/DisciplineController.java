package com.example.rozklad.controller;

import com.example.rozklad.domain.Discipline;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.DisciplineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineRepository disciplineRepository;

    public DisciplineController(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping()
    @ResponseBody
    public Iterable<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
        Optional<Discipline> searchedDiscipline = disciplineRepository.findById(id);

        if (searchedDiscipline.isPresent()) {
            return new ResponseEntity<>(searchedDiscipline.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Discipline> saveDiscipline(@RequestBody Discipline discipline) {
        try {
            disciplineRepository.save(discipline);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestParam(name = "name") String name) {
        try {
            Optional<Discipline> optionalDiscipline = disciplineRepository.findById(id);
            Discipline updatedDiscipline;

            if (optionalDiscipline.isEmpty()) {
                throw new EntityNotFoundException(new Discipline(), id);
            } else {
                updatedDiscipline = optionalDiscipline.get();
            }

            updatedDiscipline.setName(name);
            disciplineRepository.save(updatedDiscipline);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Discipline> deleteDisciplines(@RequestParam Long[] ids) {
        try {
            disciplineRepository.deleteAllById(Arrays.asList(ids));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
