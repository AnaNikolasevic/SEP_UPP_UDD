package com.project.aik_bank.dto;

public class ResponseDTO {

    private long acquirerOrderId;
    private String acquirerTimestamp;
    private long issuerOrderId;
    private String issuerTimestamp;
    private String status;

    public ResponseDTO() {

    }

    public ResponseDTO(long acquirerOrderId, String acquirerTimestamp, long issuerOrderId, String issuerTimestamp, String status) {
        this.acquirerOrderId = acquirerOrderId;
        this.acquirerTimestamp = acquirerTimestamp;
        this.issuerOrderId = issuerOrderId;
        this.issuerTimestamp = issuerTimestamp;
        this.status = status;
    }

    public long getAcquirerOrderId() {
        return acquirerOrderId;
    }

    public void setAcquirerOrderId(long acquirerOrderId) {
        this.acquirerOrderId = acquirerOrderId;
    }

    public String getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(String acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public long getIssuerOrderId() {
        return issuerOrderId;
    }

    public void setIssuerOrderId(long issuerOrderId) {
        this.issuerOrderId = issuerOrderId;
    }

    public String getIssuerTimestamp() {
        return issuerTimestamp;
    }

    public void setIssuerTimestamp(String issuerTimestamp) {
        this.issuerTimestamp = issuerTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
