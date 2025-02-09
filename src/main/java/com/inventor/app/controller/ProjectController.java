package com.inventor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventor.app.model.Project;
import com.inventor.app.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectUniqueId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectUniqueId") Long projectUniqueId) {
        projectService.deleteProject(projectUniqueId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{projectUniqueId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable("projectUniqueId") Long projectUniqueId,
            @RequestBody Project partialProject) {
        Project updatedProject = projectService.updateProject(projectUniqueId, partialProject);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectUniqueId}")
    public ResponseEntity<Project> getProjectById(@PathVariable("projectUniqueId") Long projectUniqueId) {
        Optional<Project> project = projectService.getProjectById(projectUniqueId);
        return project.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/exists/{projectNumber}")
    public ResponseEntity<Boolean> checkProjectNumberExists(@PathVariable String projectNumber) {
        boolean exists = projectService.isProjectNumberExists(projectNumber);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/status")
    public List<Project> getProjectsByStatus(@RequestParam("status") List<String> status) {
        return projectService.getAllProjectsByStatus(status);
    }
}
