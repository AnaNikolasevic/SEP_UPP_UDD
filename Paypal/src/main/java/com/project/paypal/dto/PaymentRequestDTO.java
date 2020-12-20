package com.project.paypal.dto;

public class PaymentRequestDTO {

    private Long sellerId;
    private float price;
    private String currency;

    public PaymentRequestDTO() {
        super();
    }

    public PaymentRequestDTO(Long sellerId, float price, String currency) {
        super();
        this.sellerId = sellerId;
        this.price = price;
        this.currency = currency;
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
