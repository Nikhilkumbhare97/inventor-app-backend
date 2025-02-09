package com.inventor.app.repository;

import com.inventor.app.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List; // Import List

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAll();

    boolean existsByProjectNumber(String projectNumber);

    List<Project> findByStatusIn(List<String> status);

    @Modifying
    @Query("UPDATE Project p SET p.status = :status WHERE p.projectUniqueId = :projectUniqueId") // Corrected JPQL query
    void updateProjectStatus(@Param("projectUniqueId") Long projectUniqueId, @Param("status") String status);

}