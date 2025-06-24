package com.market.Dto;

public class CampaignRequestDTO {
    private String name;
    private String description;
    private Boolean isActive; // Admin panelinden aktif/pasif ayarlanabilir

    // Getter ve Setter'lar
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
