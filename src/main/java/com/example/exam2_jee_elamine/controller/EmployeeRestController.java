package com.example.exam2_jee_elamine.controller;

import com.example.exam2_jee_elamine.model.Employee;
import com.example.exam2_jee_elamine.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('DEV', 'TEST', 'DEVOPS')")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @PreAuthorize("hasAnyRole('DEV', 'TEST', 'DEVOPS')")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @PreAuthorize("hasAnyRole('DEV', 'TEST', 'DEVOPS')")
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PreAuthorize("hasAnyRole('DEV', 'TEST', 'DEVOPS')")
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setSkills(employeeDetails.getSkills()); // Changed to setSkills
        employee.setPosts(employeeDetails.getPosts());
        employee.setProjects(employeeDetails.getProjects());

        return employeeService.save(employee);
    }

    @PreAuthorize("hasAnyRole('DEV', 'TEST', 'DEVOPS')")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
