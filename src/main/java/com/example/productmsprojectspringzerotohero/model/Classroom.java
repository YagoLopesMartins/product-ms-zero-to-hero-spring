package com.example.productmsprojectspringzerotohero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @OneToOne(mappedBy = "classroom")
    private Student student;
}
