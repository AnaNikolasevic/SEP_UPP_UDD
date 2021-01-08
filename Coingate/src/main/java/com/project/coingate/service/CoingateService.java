package com.project.coingate.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.project.coingate.Proba;
import com.project.coingate.dto.PaymentRequestDTO;
import com.project.coingate.model.PaymentOrder;
import com.project.coingate.model.Seller;
import com.project.coingate.repository.PaymentOrderRepository;
import com.project.coingate.repository.SellerRepository;

@Service
public class CoingateService {
	
	@Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private SellerRepository sellerRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ResponseEntity<String> createPayment(PaymentRequestDTO pr){
		   
		//Seller seller = sellerRepository.findOneById(pr.getSellerId());
		//System.out.println(seller.getId() + " " + seller.getEmail());
		Seller seller = new Seller();
		sellerRepository.save(seller);
		PaymentOrder po = new PaymentOrder();
		po.setSeller(seller);
		po.setPrice(pr.getPrice());
		po.setCurrency(pr.getCurrency());
		paymentOrderRepository.save(po);
		    
		String url = "https://api-sandbox.coingate.com/v2/orders";
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("order_id", 1 + "");
		map.add("price_amount", pr.getPrice() + "");
		map.add("price_currency", pr.getCurrency());
		map.add("receive_currency", pr.getCurrency());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Token qkxwXrxD6y5hth7xFs_-yG_MyCgAR-Vm_sGykE-J");
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		
		System.out.println("Sending request for bitcoin payment");
		RestTemplate restClient = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restClient.postForEntity(url, request, String.class);
		System.out.println("Payment created successfully");
		JsonParser basicJsonParser = new BasicJsonParser();
		String paymentUrl = (String)basicJsonParser.parseMap(response.getBody()).get("payment_url");
		
		System.out.println("Redirecting user to allow link");
		
		logger.info("Coingate order coingateId="+ "" +" created sellerId="+pr.getSellerId());
		
		po.setPaymentId("");
		paymentOrderRepository.save(po);
		
		return new ResponseEntity<String>(paymentUrl, HttpStatus.OK);		   
	}
}
