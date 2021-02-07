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
	private String merchantId;
	private String password;
	private String PAN;
	

	public SellerDTO(Long id, String coingateToken, String email, String paypalClientId, String paypalSecret,
			String merchantId, String password, String pAN) {
		super();
		this.id = id;
		this.coingateToken = coingateToken;
		this.email = email;
		this.paypalClientId = paypalClientId;
		this.paypalSecret = paypalSecret;
		this.merchantId = merchantId;
		this.password = password;
		PAN = pAN;
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
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPAN() {
		return PAN;
	}
	public void setPAN(String pAN) {
		PAN = pAN;
	}
}
