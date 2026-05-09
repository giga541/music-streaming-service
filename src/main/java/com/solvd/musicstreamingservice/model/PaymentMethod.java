package com.solvd.musicstreamingservice.model;

public class PaymentMethod {

    private Long id;
    private String type;
    private String lastFourNumber;
    private boolean isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastFourNumber() {
        return lastFourNumber;
    }

    public void setLastFourNumber(String lastFourNumber) {
        this.lastFourNumber = lastFourNumber;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}