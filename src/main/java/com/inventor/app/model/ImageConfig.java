package com.inventor.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "image_config")
public class ImageConfig {

    @Id
    @Column(name = "image_name")
    private String imageName;

    @Column(name = "config", columnDefinition = "JSON") // Important: Use appropriate JSON column type
    private String config; // Store the JSON string

    // Getters and setters
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public ImageConfig() {
    }

    public ImageConfig(String imageName, String config) {
        this.imageName = imageName;
        this.config = config;
    }
}
