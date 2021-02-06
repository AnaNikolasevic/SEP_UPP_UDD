package com.project.aik_bank.model;

import javax.persistence.*;

@Entity
public class PCCRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column
    private Long acquirerOrderId;
    @Column
    private String acquirerTimestamp;
    @Column
    private String pan;
    @Column
    private long securityCode;
    @Column
    private String cardHolderName;
    @Column
    private String expirationDate;
    @Column
    private float amount;
    @Column
    private String bankName;

    public PCCRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcquirerOrderId() {
        return acquirerOrderId;
    }

    public void setAcquirerOrderId(Long acquirerOrderId) {
        this.acquirerOrderId = acquirerOrderId;
    }

    public String getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(String acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public long getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(long securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
