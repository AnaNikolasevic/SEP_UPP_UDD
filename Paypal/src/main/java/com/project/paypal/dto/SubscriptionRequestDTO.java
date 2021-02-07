package com.project.paypal.dto;

public class SubscriptionRequestDTO {

    private long sellerId;

    private String name;
    private String description;
    private String type;

    private String frequency;
    private String frequencyIntrval;
    private String cycles;

    private String amount;
    private String currency;

    private String successUrl;
    private String failureUrl;

    public SubscriptionRequestDTO(long sellerId, String name, String description, String type, String frequency, String frequencyIntrval, String cycles, String amount, String currency, String successUrl, String failureUrl) {
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.frequency = frequency;
        this.frequencyIntrval = frequencyIntrval;
        this.cycles = cycles;
        this.amount = amount;
        this.currency = currency;
        this.successUrl = successUrl;
        this.failureUrl = failureUrl;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyIntrval() {
        return frequencyIntrval;
    }

    public void setFrequencyIntrval(String frequencyIntrval) {
        this.frequencyIntrval = frequencyIntrval;
    }

    public String getCycles() {
        return cycles;
    }

    public void setCycles(String cycles) {
        this.cycles = cycles;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }
}
