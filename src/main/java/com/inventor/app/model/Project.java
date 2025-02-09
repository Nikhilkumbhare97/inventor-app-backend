package com.inventor.app.model;

import jakarta.persistence.*; // Use jakarta.persistence (for Jakarta EE)
import java.time.LocalDate; // Or java.time.LocalDate for modern date handling

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_unique_id") // Consistent naming (snake_case)
    private Long projectUniqueId;

    @Column(name = "project_id", nullable = false, length = 200)
    private String projectId;

    @Column(name = "project_name", nullable = false, length = 200)
    private String projectName;

    @Column(name = "project_number", unique = true, nullable = false, length = 100)
    private String projectNumber;

    @Column(name = "client_name", nullable = false, length = 200)
    private String clientName;

    @Column(name = "created_by", nullable = false, length = 200)
    private String createdBy;

    @Column(name = "prepared_by", nullable = false, length = 200)
    private String preparedBy;

    @Column(name = "checked_by", nullable = false, length = 200)
    private String checkedBy;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    public Project() {
    }

    public Project(String projectId, String projectName, String projectNumber, String clientName, String createdBy,
            String preparedBy,
            String checkedBy, LocalDate date, String status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectNumber = projectNumber;
        this.clientName = clientName;
        this.createdBy = createdBy;
        this.preparedBy = preparedBy;
        this.checkedBy = checkedBy;
        this.date = date;
        this.status = status;
    }

    public Long getProjectUniqueId() {
        return projectUniqueId;
    }

    public void setProjectUniqueId(Long projectUniqueId) {
        this.projectUniqueId = projectUniqueId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project [projectUniqueId=" + projectUniqueId + ", projectId=" + projectId + ", projectName="
                + projectName + ", projectNumber=" + projectNumber + ", clientName=" + clientName + ", createdBy="
                + createdBy + ", preparedBy=" + preparedBy + ", checkedBy=" + checkedBy + ", date=" + date + ", status="
                + status + "]";
    }

}