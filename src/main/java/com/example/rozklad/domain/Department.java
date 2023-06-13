package com.example.rozklad.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "department_name", nullable = false)
    private String name;

    @Column(name = "department_short_name", nullable = false)
    private String shortName;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Department() {
    }

    public Department(String name, String shortName, Faculty faculty) {
        this.name = name;
        this.shortName = shortName;
        this.faculty = faculty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
