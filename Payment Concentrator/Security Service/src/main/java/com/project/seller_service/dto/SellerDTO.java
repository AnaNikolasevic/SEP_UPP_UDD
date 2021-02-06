package com.project.seller_service.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SellerDTO {
	
	private Long id;
	private String coingateToken;
	private String email;
	private String paypalClientId;
	private String paypalSecret;
	
	public SellerDTO(Long id, String coingateToken, String email, String paypalClientId, String paypalSecret) {
		super();
		this.id = id;
		this.coingateToken = coingateToken;
		this.email = email;
		this.paypalClientId = paypalClientId;
		this.paypalSecret = paypalSecret;
	}
	public SellerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoingateToken() {
		return coingateToken;
	}
	public void setCoingateToken(String coingateToken) {
		this.coingateToken = coingateToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaypalClientId() {
		return paypalClientId;
	}
	public void setPaypalClientId(String paypalClientId) {
		this.paypalClientId = paypalClientId;
	}
	public String getPaypalSecret() {
		return paypalSecret;
	}
	public void setPaypalSecret(String paypalSecret) {
		this.paypalSecret = paypalSecret;
	}
	
	
}
