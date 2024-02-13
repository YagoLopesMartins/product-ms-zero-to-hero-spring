package com.example.productmsprojectspringzerotohero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id")
    private Student student;
}
