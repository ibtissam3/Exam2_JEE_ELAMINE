package com.example.exam2_jee_elamine.controller;

import com.example.exam2_jee_elamine.service.EmployeeService;
import com.example.exam2_jee_elamine.model.Employee; // Make sure to import the Employee model
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('MANAGER', 'TECHLEAD')")
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees"; // Nom de la vue Thymeleaf
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'TECHLEAD')")
    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam Long employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/employees";
    }

    // Optional: If you have a method to add an employee
    @PreAuthorize("hasAnyRole('MANAGER', 'TECHLEAD')")
    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee"; // Create addEmployee.html for this view
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'TECHLEAD')")
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }
}
