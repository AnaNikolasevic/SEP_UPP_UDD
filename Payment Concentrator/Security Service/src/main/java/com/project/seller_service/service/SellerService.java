package com.project.seller_service.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(SellerService.class);
	
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
			seller.setMerchant_id(sellerDTO.getMerchantId());
			seller.setMerchant_password(sellerDTO.getPassword());
		}
		System.out.println(response);
		seller.getPaymentTypes().add(paymentType);
		repository.save(seller);
		return seller.getPaymentTypes();
	}

	private ResponseEntity<String> addCard(SellerDTO sellerDTO) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8090/seller";
		JSONObject map = new JSONObject();
		map.put("merchantId", sellerDTO.getMerchantId() + "");
		map.put("password", sellerDTO.getPassword() + "");
		map.put("pan", sellerDTO.getPAN());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		System.out.println("Sending seller card request to card service ...");
		logger.info("Sending seller card request to card service ...");
		RestTemplate restClient = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(map.toString(), headers);
		ResponseEntity<String> response = restClient.postForEntity(url, request, String.class);
		return response;
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
		logger.info("Sending seller bitcoin request to bitcoin service ...");
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
		logger.info("Sending seller paypal request to paypal service ...");
		RestTemplate restClient = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(map.toString(), headers);
		ResponseEntity<String> response = restClient.postForEntity(url, request, String.class);
		return response;
	}

	public void checkIfExist(int sellerId) {
		// TODO Auto-generated method stub
		if(!repository.existsById(Long.valueOf(sellerId))) {
			logger.info("This seller is not initialized. Creating seller . . .");
			Seller seller = new Seller();
			seller.setPaymentTypes(new ArrayList<PaymentType>());
			repository.save(seller);
			logger.info("Seller created in Payment Concentrator.");
		}
	}



}
