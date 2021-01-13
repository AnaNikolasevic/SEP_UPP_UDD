package com.project.seller_service.dto;

import javax.persistence.Column;

public class OrderRequestDto {

	private Long id;
	private Long sellerId;
	private String price;
	private String currency;
	private String merchant_timestamp;
	private String merchant_id;
	private String merchant_password;
	private String success_url;
	private String failed_url;
	private String error_url;
	private String payment_type;
	private String status;


	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public OrderRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public String getStatus() {
		return status;
	}
}