package com.project.seller_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Entity
public class PaymentType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_type_id")
	private Long id;
	
	@Column
	private String name;
	@Column
	private String success_url;
	@Column
	private String failed_url;
	@Column
	private String error_url;

	public PaymentType() {
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
