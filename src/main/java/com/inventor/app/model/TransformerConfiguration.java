package com.inventor.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transformer_configuration")
public class TransformerConfiguration {

    @Id
    @Column(name = "project_unique_id", nullable = false)
    private Long projectUniqueId;

    @Column(name = "tank_details", columnDefinition = "JSON")
    private String tankDetails;

    @Column(name = "lv_turret_details", columnDefinition = "JSON")
    private String lvTurretDetails;

    @Column(name = "top_cover_details", columnDefinition = "JSON")
    private String topCoverDetails;

    @Column(name = "hv_turret_details", columnDefinition = "JSON")
    private String hvTurretDetails;

    @Column(name = "piping", columnDefinition = "JSON")
    private String piping;

    @Column(name = "lv_trunking_details", columnDefinition = "JSON")
    private String lvTrunkingDetails;

    public TransformerConfiguration() {
    }

    public TransformerConfiguration(Long projectUniqueId, String tankDetails, String lvTurretDetails,
            String topCoverDetails, String hvTurretDetails, String piping, String lvTrunkingDetails) {
        this.projectUniqueId = projectUniqueId;
        this.tankDetails = tankDetails;
        this.lvTurretDetails = lvTurretDetails;
        this.topCoverDetails = topCoverDetails;
        this.hvTurretDetails = hvTurretDetails;
        this.piping = piping;
        this.lvTrunkingDetails = lvTrunkingDetails;
    }

    public Long getProjectUniqueId() {
        return projectUniqueId;
    }

    public void setProjectUniqueId(Long projectUniqueId) {
        this.projectUniqueId = projectUniqueId;
    }

    public String getTankDetails() {
        return tankDetails;
    }

    public void setTankDetails(String tankDetails) {
        this.tankDetails = tankDetails;
    }

    public String getLvTurretDetails() {
        return lvTurretDetails;
    }

    public void setLvTurretDetails(String lvTurretDetails) {
        this.lvTurretDetails = lvTurretDetails;
    }

    public String getTopCoverDetails() {
        return topCoverDetails;
    }

    public void setTopCoverDetails(String topCoverDetails) {
        this.topCoverDetails = topCoverDetails;
    }

    public String getHvTurretDetails() {
        return hvTurretDetails;
    }

    public void setHvTurretDetails(String hvTurretDetails) {
        this.hvTurretDetails = hvTurretDetails;
    }

    public String getPiping() {
        return piping;
    }

    public void setPiping(String piping) {
        this.piping = piping;
    }

    public String getLvTrunkingDetails() {
        return lvTrunkingDetails;
    }

    public void setLvTrunkingDetails(String lvTrunkingDetails) {
        this.lvTrunkingDetails = lvTrunkingDetails;
    }

    @Override
    public String toString() {
        return "TransformerConfiguration{" +
                "projectUniqueId=" + projectUniqueId +
                ", tankDetails='" + tankDetails + '\'' +
                ", lvTurretDetails='" + lvTurretDetails + '\'' +
                ", topCoverDetails='" + topCoverDetails + '\'' +
                ", hvTurretDetails='" + hvTurretDetails + '\'' +
                ", piping='" + piping + '\'' +
                ", lvTrunkingDetails='" + lvTrunkingDetails + '\'' +
                '}';
    }
}
