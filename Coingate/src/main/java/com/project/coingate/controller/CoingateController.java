package com.project.coingate.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.coingate.dto.PaymentRequestDTO;
import com.project.coingate.model.PaymentOrder;
import com.project.coingate.service.CoingateService;
import com.project.coingate.service.PaymentOrderService;
import com.project.coingate.utils.JwtUtils;


@CrossOrigin
@RestController
public class CoingateController {
	
    @Autowired
    CoingateService coingateService;

    @Autowired
    PaymentOrderService paymentOrderService;
    
	@Autowired
	JwtUtils jwtUtils;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping(path= "/pay", consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment(@RequestHeader(value = "Authorization") String token) throws Throwable{

        System.out.println("token" + token);
        PaymentRequestDTO pr = jwtUtils.getPaymentRequest(token);
        return new ResponseEntity<>(coingateService.createPayment(pr), HttpStatus.OK);
    }
	
   /* @GetMapping(path= "/makeOrder")
    public ResponseEntity createPayment() {
		Proba proba = new Proba();
		proba.setPrice_amount(1.0);
		proba.setPrice_currency("BTC");
		proba.setReceive_currency("BTC");
		String url = "https://api-sandbox.coingate.com/v2/orders";
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("order_id", 1 + "");
		map.add("price_amount", 1 + "");
		map.add("price_currency", "USD");
		map.add("receive_currency", "USD");
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
		return new ResponseEntity<String>(paymentUrl, HttpStatus.OK);

    }*/
	
	@PutMapping(path = "/success/{paymentOrderId}")
	public String success(@PathVariable String paymentOrderId) {	
		System.out.println("success");
		paymentOrderService.success(paymentOrderId);
		return "success";
	}
	
	@PutMapping(path = "/cancel/{paymentOrderId}")
	public String cancel(@PathVariable String paymentOrderId) {	
		System.out.println("cancel");
		paymentOrderService.error(paymentOrderId);
		return "cancel";
	}
    
}
