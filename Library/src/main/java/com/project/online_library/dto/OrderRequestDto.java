package com.project.online_library.dto;

public class OrderRequestDto {

	private Long sellerId;
	private String price;
	private String currency;
	private String merchant_timestamp;

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

	public OrderRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
