package com.inventor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.model.ImageConfig;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ImageConfigRepository extends JpaRepository<ImageConfig, String> {
    // Add any custom queries if needed. For example, to get all image names:
    @Query("SELECT i.imageName FROM ImageConfig i")
    List<String> getAllImageNames();

}