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
	
	@ManyToMany
	@JoinTable(
			  name = "seller_payment_types", 
			  joinColumns = @JoinColumn(name = "seller_id"), 
			  inverseJoinColumns = @JoinColumn(name = "payment_type_id"))
	private List<PaymentType> paymentTypes;



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

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
