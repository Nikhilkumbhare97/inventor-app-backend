package com.inventor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventor.app.model.Project;
import com.inventor.app.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        // Set default status to "Active" if not provided
        if (project.getStatus() == null || project.getStatus().isEmpty()) {
            project.setStatus("Active");
        }
        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long projectUniqueId) {
        projectRepository.updateProjectStatus(projectUniqueId, "Deleted");
    }

    public Project updateProject(Long projectUniqueId, Project partialProject) {
        return projectRepository.findById(projectUniqueId)
                .map(existingProject -> {
                    // Update only non-null fields
                    if (partialProject.getProjectName() != null) {
                        existingProject.setProjectName(partialProject.getProjectName());
                    }
                    if (partialProject.getProjectNumber() != null) {
                        existingProject.setProjectNumber(partialProject.getProjectNumber());
                    }
                    if (partialProject.getProjectId() != null) {
                        existingProject.setProjectId(partialProject.getProjectId());
                    }
                    if (partialProject.getClientName() != null) {
                        existingProject.setClientName(partialProject.getClientName());
                    }
                    if (partialProject.getCreatedBy() != null) {
                        existingProject.setCreatedBy(partialProject.getCreatedBy());
                    }
                    if (partialProject.getPreparedBy() != null) {
                        existingProject.setPreparedBy(partialProject.getPreparedBy());
                    }
                    if (partialProject.getCheckedBy() != null) {
                        existingProject.setCheckedBy(partialProject.getCheckedBy());
                    }
                    if (partialProject.getDate() != null) {
                        existingProject.setDate(partialProject.getDate());
                    }
                    return projectRepository.save(existingProject);
                })
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectUniqueId));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getAllProjectsByStatus(List<String> status) {
        return projectRepository.findByStatusIn(status);
    }

    public Optional<Project> getProjectById(Long projectUniqueId) {
        return projectRepository.findById(projectUniqueId);
    }

    public boolean isProjectNumberExists(String projectNumber) {
        return projectRepository.existsByProjectNumber(projectNumber);
    }

}
