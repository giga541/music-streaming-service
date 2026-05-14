package com.solvd.musicstreamingservice.model;

public class PaymentMethod {

    private Long id;
    private String type;
    private String lastFourNumber;
    private boolean defaultMethod;

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

    public boolean defaultMethod() {
        return defaultMethod;
    }

    public void setDefaultMethod(boolean defaultMethod) {
        this.defaultMethod = defaultMethod;
    }
}