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

    @Column(name = "fk_faculty_id", nullable = false)
    private Long facultyId;

    public Department() {
    }

    public Department(String name, String shortName, Long faculty_id) {
        this.name = name;
        this.shortName = shortName;
        this.facultyId = faculty_id;
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

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public String toString() {
        return "DepartmentController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", faculty_id=" + facultyId +
                '}';
    }
}
