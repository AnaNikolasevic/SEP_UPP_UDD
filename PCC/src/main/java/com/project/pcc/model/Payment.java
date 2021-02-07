package com.project.pcc.model;

import javax.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    private String IssuerBank;

    @Column
    private String AcquirerBank;

    @Column
    private Long acquirerOrderId;

    @Column
    private Long issuerOrderId;

    @Column
    private String acquirerTimestamp;

    @Column
    private String issuerTimestamp;

    @Column
    private  String status;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssuerBank() {
        return IssuerBank;
    }

    public void setIssuerBank(String issuerBank) {
        IssuerBank = issuerBank;
    }

    public String getAcquirerBank() {
        return AcquirerBank;
    }

    public void setAcquirerBank(String acquirerBank) {
        AcquirerBank = acquirerBank;
    }

    public Long getAcquirerOrderId() {
        return acquirerOrderId;
    }

    public void setAcquirerOrderId(Long acquirerOrderId) {
        this.acquirerOrderId = acquirerOrderId;
    }

    public Long getIssuerOrderId() {
        return issuerOrderId;
    }

    public void setIssuerOrderId(Long issuerOrderId) {
        this.issuerOrderId = issuerOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(String acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public String getIssuerTimestamp() {
        return issuerTimestamp;
    }

    public void setIssuerTimestamp(String issuerTimestamp) {
        this.issuerTimestamp = issuerTimestamp;
    }
}
