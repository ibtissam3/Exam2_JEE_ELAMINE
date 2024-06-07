package com.example.exam2_jee_elamine.controller;

import com.example.exam2_jee_elamine.model.Employee;
import com.example.exam2_jee_elamine.model.Project;
import com.example.exam2_jee_elamine.service.EmployeeService;
import com.example.exam2_jee_elamine.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/assign")
    public String showAssignForm(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("projects", projectService.findAll());
        return "assign"; // Nom de la vue JSP
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/assign")
    public String assignProject(@RequestParam Long employeeId, @RequestParam Long projectId, @RequestParam int implication) {
        Employee employee = employeeService.findById(employeeId).orElseThrow();
        Project project = projectService.findById(projectId).orElseThrow();

        // Ajouter le projet à la liste des projets de l'employé
        employee.getProjects().add(project);
        employeeService.save(employee);

        // Logique pour gérer le pourcentage d'implication peut être ajouté ici

        return "redirect:/projects/assign";
    }
}
