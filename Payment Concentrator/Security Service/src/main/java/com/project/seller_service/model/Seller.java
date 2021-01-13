package com.project.seller_service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Seller {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "merchant_id")
	private String merchant_id;

	@Column(name = "merchant_password")
	private String merchant_password;

	
	@ManyToMany
	@JoinTable(
			  name = "seller_payment_types", 
			  joinColumns = @JoinColumn(name = "seller_id"), 
			  inverseJoinColumns = @JoinColumn(name = "payment_type_id"))
	private List<PaymentType> paymentTypes;


	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
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




	
}
