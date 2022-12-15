package com.example.rozklad.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "teachers")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "teacher_name", nullable = false)
    private String name;

    @Column(name = "teacher_surname", nullable = false)
    private String surname;

    @Column(name = "teacher_email", nullable = false)
    private String email;

    @Column(name = "teacher_phone", nullable = false)
    private String phone;

    public Teacher() {
    }

    public Teacher(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
