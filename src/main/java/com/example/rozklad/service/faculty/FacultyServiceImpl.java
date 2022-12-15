package com.example.rozklad.service.faculty;

import com.example.rozklad.domain.Faculty;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void add(Map<String, String> requestParams) {
        String name = requestParams.get("name");
        String shortName = requestParams.get("shortName");

        Faculty newFaculty = new Faculty(name, shortName);
        facultyRepository.save(newFaculty);
    }

    @Override
    public void update(Long id, Map<String, String> requestParams) {
        Optional<Faculty> oldFaculty = facultyRepository.findById(id);

        if (oldFaculty.isEmpty()) {
            throw new EntityNotFoundException(Faculty.class, id);
        }

        String name = requestParams.get("name");
        String shortName = requestParams.get("shortName");

        Faculty updatedFaculty = oldFaculty.get();
        updatedFaculty.setName(name);
        updatedFaculty.setShortName(shortName);
        facultyRepository.save(updatedFaculty);
    }
}
