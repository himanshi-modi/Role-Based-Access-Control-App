package com.project.demo.Entity.model;

import com.project.demo.Entity.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Size(min=8)
    private String password;


    private Roles roles;





}
