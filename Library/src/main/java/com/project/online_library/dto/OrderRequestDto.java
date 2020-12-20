package com.project.online_library.dto;

public class OrderRequestDto {
	private Long selerId;
	private String price;
	private String currency;
	public Long getSelerId() {
		return selerId;
	}
	public void setSelerId(Long selerId) {
		this.selerId = selerId;
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
