package com.example.productmsprojectspringzerotohero.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @ManyToMany(mappedBy = "teachers")
    private List<Student> students;

}
