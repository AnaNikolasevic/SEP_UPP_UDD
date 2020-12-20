package com.project.paypal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PaymentOrder {

    @Id
    private Long id;

    @Column
    private String paymentId;

    @Column
    private Long payerId;

    @Column
    private float price;

    @Column
    private String currency;

    @Column
    private String method;

    @Column
    private String intent;

    @Column
    private String description;

    @Column
    private PaymentOrderStatus status = PaymentOrderStatus.CREATED;

    @ManyToOne
    private Seller seller;

}
