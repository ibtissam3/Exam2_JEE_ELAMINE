package com.example.exam2_jee_elamine.repository;

import com.example.exam2_jee_elamine.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
