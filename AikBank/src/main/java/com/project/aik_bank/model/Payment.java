package com.project.aik_bank.model;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    //customer who receives the payment
    @ManyToOne
    private Customer payee;
    //paying customer
    @ManyToOne
    private Customer payeer;

    @Column
    private float amount;
    @Column
    private String merchant_timestamp;
    @Column
    private Long merchant_order_id;
    @Column
    private String success_url;
    @Column
    private String failed_url;
    @Column
    private String error_url;
    @Column
    private String status;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getPayee() {
        return payee;
    }

    public void setPayee(Customer payee) {
        this.payee = payee;
    }

    public Customer getPayeer() {
        return payeer;
    }

    public void setPayeer(Customer payeer) {
        this.payeer = payeer;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getMerchant_timestamp() {
        return merchant_timestamp;
    }

    public void setMerchant_timestamp(String merchant_timestamp) {
        this.merchant_timestamp = merchant_timestamp;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMerchant_order_id() {
        return merchant_order_id;
    }

    public void setMerchant_order_id(Long merchant_order_id) {
        this.merchant_order_id = merchant_order_id;
    }
}
