package com.project.paypal.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 100)
    private String id;

    @Column
    private long sellerId;

    @Column
    private String planId;

    @Column
    private String aggrementToken;

    @Column
    private String aggrementId;

    @Column
    private String confirmationUrl;

    @Column
    private String status;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String successUrl;

    @Column
    private String failureUrl;


    public Subscription(String id, long sellerId, String planId, String aggrementToken, String aggrementId, String confirmationUrl, String status, String name, String description, String successUrl, String failureUrl) {
        this.id = id;
        this.sellerId = sellerId;
        this.planId = planId;
        this.aggrementToken = aggrementToken;
        this.aggrementId = aggrementId;
        this.confirmationUrl = confirmationUrl;
        this.status = status;
        this.name = name;
        this.description = description;
        this.successUrl = successUrl;
        this.failureUrl = failureUrl;
    }

    public Subscription() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getAggrementToken() {
        return aggrementToken;
    }

    public void setAggrementToken(String aggrementToken) {
        this.aggrementToken = aggrementToken;
    }

    public String getAggrementId() {
        return aggrementId;
    }

    public void setAggrementId(String aggrementId) {
        this.aggrementId = aggrementId;
    }

    public String getConfirmationUrl() {
        return confirmationUrl;
    }

    public void setConfirmationUrl(String confirmationUrl) {
        this.confirmationUrl = confirmationUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
