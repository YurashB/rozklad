package com.example.rozklad.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String name;

    @Column(name = "course", nullable = false)
    private int course;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Group() {
    }

    public Group(String name, int course, Department department) {
        this.name = name;
        this.course = course;
        this.department = department;
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

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


}
