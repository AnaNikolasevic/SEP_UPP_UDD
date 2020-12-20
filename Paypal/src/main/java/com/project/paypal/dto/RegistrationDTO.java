package com.project.paypal.dto;

public class RegistrationDTO {

    private String clientId;
    private String clientSecret;
    private String email;

    public RegistrationDTO(String clientId, String clientSecret, String email) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.email = email;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
