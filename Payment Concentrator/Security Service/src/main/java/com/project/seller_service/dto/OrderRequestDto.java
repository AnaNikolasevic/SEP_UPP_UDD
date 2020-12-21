package com.project.seller_service.dto;

public class OrderRequestDto {
	private Long sellerId;
	private String price;
	private String currency;

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
	public OrderRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
