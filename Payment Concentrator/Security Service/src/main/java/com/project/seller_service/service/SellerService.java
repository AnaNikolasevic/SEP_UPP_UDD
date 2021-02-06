package com.project.seller_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.project.seller_service.dto.SellerDTO;
import com.project.seller_service.model.PaymentType;
import com.project.seller_service.model.Seller;
import com.project.seller_service.repository.SellerRepository;

import net.minidev.json.JSONObject;

@Service
public class SellerService {
	@Autowired
	private SellerRepository repository;
	
	public List<Seller> getAll() {
	    return repository.findAll();
	}
	
	public Seller getOne(Long sellerId) {
	    return repository.getOne(sellerId);
	}

	public List<PaymentType> getSellerPaymentTypes(Long sellerId) {
		// TODO Auto-generated method stub
		return repository.getOne(sellerId).getPaymentTypes();
	}

	public List<PaymentType> addSellerPaymentTypes(Long sellerId, PaymentType paymentType, SellerDTO sellerDTO) {
		// TODO Auto-generated method stub
		Seller seller = repository.getOne(sellerId);
		sellerDTO.setId(sellerId);
		ResponseEntity<String> response = null;
		if(paymentType.getName().equals("paypal")) {
			response = addPaypal(sellerDTO);
		} else if (paymentType.getName().equals("bitcoin")) {
			response = addBitcoin(sellerDTO);
		} else if (paymentType.getName().equals("card")) {
			response = addCard(sellerDTO);
		}
		System.out.println(response);
		seller.getPaymentTypes().add(paymentType);
		repository.save(seller);
		return seller.getPaymentTypes();
	}

	private ResponseEntity addCard(SellerDTO sellerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<String> addBitcoin(SellerDTO sellerDTO) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8089/seller";
		JSONObject map = new JSONObject();
		map.put("id", sellerDTO.getId() + "");
		map.put("coingateToken", sellerDTO.getCoingateToken() + "");
		map.put("email", sellerDTO.getEmail());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		System.out.println("Sending seller bitcoin request to bitcoin service ...");
		RestTemplate restClient = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(map.toString(), headers);
		ResponseEntity<String> response = restClient.postForEntity(url, request, String.class);
		return response;
	}

	private ResponseEntity<String> addPaypal(SellerDTO sellerDTO) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8081/seller";
		JSONObject map = new JSONObject();
		map.put("id", sellerDTO.getId() + "");
		map.put("paypalClientId", sellerDTO.getPaypalClientId() + "");
		map.put("email", sellerDTO.getEmail());
		map.put("paypalSecret", sellerDTO.getPaypalSecret());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		System.out.println("Sending seller paypal request to paypal service ...");
		RestTemplate restClient = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(map.toString(), headers);
		ResponseEntity<String> response = restClient.postForEntity(url, request, String.class);
		return response;
	}

	public void checkIfExist(int sellerId) {
		// TODO Auto-generated method stub
		if(!repository.existsById(Long.valueOf(sellerId))) {
			Seller seller = new Seller();
			seller.setPaymentTypes(new ArrayList<PaymentType>());
			repository.save(seller);
		}
	}



}
