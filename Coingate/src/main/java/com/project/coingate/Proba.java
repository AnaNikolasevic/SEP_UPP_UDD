package com.project.coingate;

public class Proba {
	private double price_amount;
	private String price_currency;
	private String receive_currency;
	private String payment_url;
	public double getPrice_amount() {
		return price_amount;
	}
	public Proba() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPayment_url() {
		return payment_url;
	}
	public void setPayment_url(String payment_url) {
		this.payment_url = payment_url;
	}
	public void setPrice_amount(double price_amount) {
		this.price_amount = price_amount;
	}
	public String getPrice_currency() {
		return price_currency;
	}
	public void setPrice_currency(String price_currency) {
		this.price_currency = price_currency;
	}
	public String getReceive_currency() {
		return receive_currency;
	}
	public void setReceive_currency(String receive_currency) {
		this.receive_currency = receive_currency;
	}
	
}
