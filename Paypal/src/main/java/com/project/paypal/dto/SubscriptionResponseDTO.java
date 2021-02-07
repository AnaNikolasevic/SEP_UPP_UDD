package com.project.paypal.dto;

public class SubscriptionResponseDTO {

    private String subscriptionId;
    private String paymentUrl;
    private String cancelUrl;
    private String token;

   public SubscriptionResponseDTO(){

   }

    public SubscriptionResponseDTO(String subscriptionId, String paymentUrl, String cancelUrl, String token) {
        this.subscriptionId = subscriptionId;
        this.paymentUrl = paymentUrl;
        this.cancelUrl = cancelUrl;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
}
