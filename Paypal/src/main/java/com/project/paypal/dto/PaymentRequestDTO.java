package com.project.paypal.dto;

public class PaymentRequestDTO {

    private Long id;
    private Long sellerId;
    private float price;
    private String currency;

    public PaymentRequestDTO() {
        super();
    }

    public PaymentRequestDTO(Long id, Long sellerId, float price, String currency) {
        super();
        this.id = id;
        this.sellerId = sellerId;
        this.price = price;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
