package com.example.exam2_jee_elamine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double budget;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees;

    // Getters and setters
}
