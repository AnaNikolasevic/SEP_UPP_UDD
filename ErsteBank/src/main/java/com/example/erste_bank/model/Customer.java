package com.example.erste_bank.model;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String merchantId;
    @Column
    private String password;
    @Column
    private double availableFunds;
    @Column
    private double reservedFunds;
    @Column
    private String currency;

    @Column(unique = true)
    private String PAN;
    @Column
    private long creditCardSecurityNumber;
    @Column
    private String cardHolderName;
    @Column
    private String expirationDate;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(double availableFunds) {
        this.availableFunds = availableFunds;
    }

    public double getReservedFunds() {
        return reservedFunds;
    }

    public void setReservedFunds(double reservedFunds) {
        this.reservedFunds = reservedFunds;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public long getCreditCardSecurityNumber() {
        return creditCardSecurityNumber;
    }

    public void setCreditCardSecurityNumber(long creditCardSecurityNumber) {
        this.creditCardSecurityNumber = creditCardSecurityNumber;
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
}
