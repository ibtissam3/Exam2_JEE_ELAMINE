package com.example.exam2_jee_elamine.repository;

import com.example.exam2_jee_elamine.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
