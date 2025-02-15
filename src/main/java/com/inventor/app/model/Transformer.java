package com.inventor.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transformer_details")
public class Transformer {

    @Id
    @Column(name = "project_unique_id")
    private Long projectUniqueId;

    @Column(name = "transformer_type", nullable = false, length = 100)
    private String transformerType;

    @Column(name = "design_type", nullable = false, length = 50)
    private String designType;

    public Transformer() {
    }

    public Transformer(Long projectUniqueId, String transformerType, String designType) {
        this.projectUniqueId = projectUniqueId;
        this.transformerType = transformerType;
        this.designType = designType;
    }

    public Long getProjectUniqueId() {
        return projectUniqueId;
    }

    public void setProjectUniqueId(Long projectUniqueId) {
        this.projectUniqueId = projectUniqueId;
    }

    public String getTransformerType() {
        return transformerType;
    }

    public void setTransformerType(String transformerType) {
        this.transformerType = transformerType;
    }

    public String getDesignType() {
        return designType;
    }

    public void setDesignType(String designType) {
        this.designType = designType;
    }

    @Override
    public String toString() {
        return "Transformer{" +
                "projectUniqueId='" + projectUniqueId + '\'' +
                ", transformerType='" + transformerType + '\'' +
                ", designType='" + designType + '\'' +
                '}';
    }
}
