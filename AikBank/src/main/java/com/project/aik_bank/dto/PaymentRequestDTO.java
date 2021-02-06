package com.project.aik_bank.dto;


//crate payment from token
public class PaymentRequestDTO {

    private Long id;
    private Long sellerId;
    private float price;
    private String currency;
    private String merchant_timestamp;
    private String merchant_id;
    private String merchant_password;
    private String success_url;
    private String failed_url;
    private String error_url;

    public PaymentRequestDTO() {
        super();
    }

    public PaymentRequestDTO(Long sellerId, float price, String currency, String merchant_timestamp) {
        this.sellerId = sellerId;
        this.price = price;
        this.currency = currency;
        this.merchant_timestamp = merchant_timestamp;
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

    public String getMerchant_timestamp() {
        return merchant_timestamp;
    }

    public void setMerchant_timestamp(String merchant_timestamp) {
        this.merchant_timestamp = merchant_timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_password() {
        return merchant_password;
    }

    public void setMerchant_password(String merchant_password) {
        this.merchant_password = merchant_password;
    }

    public String getSuccess_url() {
        return success_url;
    }

    public void setSuccess_url(String success_url) {
        this.success_url = success_url;
    }

    public String getFailed_url() {
        return failed_url;
    }

    public void setFailed_url(String failed_url) {
        this.failed_url = failed_url;
    }

    public String getError_url() {
        return error_url;
    }

    public void setError_url(String error_url) {
        this.error_url = error_url;
    }
}
