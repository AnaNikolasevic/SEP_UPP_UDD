package com.project.seller_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.seller_service.model.PaymentType;
import com.project.seller_service.model.Seller;
import com.project.seller_service.service.PaymentTypeService;
import com.project.seller_service.service.SellerService;

@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	SellerService service;
	@Autowired
	PaymentTypeService servicePT;
	@GetMapping("/paymentTypes/{sellerId}")
    public ResponseEntity<?> getSellerPaymentTypes(@PathVariable int sellerId) {
        return new ResponseEntity<List<PaymentType>>(service.getSellerPaymentTypes(Long.valueOf(sellerId)), HttpStatus.OK);
    }
	

	@PutMapping("/paymentTypes/{sellerId}/{paymentTypeId}")
    public ResponseEntity<?> getSellerPaymentTypes(@PathVariable int sellerId, @PathVariable int paymentTypeId) {
		PaymentType paymentType = (PaymentType) servicePT.getOne(Long.valueOf(paymentTypeId));
		if(service.getSellerPaymentTypes(Long.valueOf(sellerId)).contains(paymentType)) {
	        return new ResponseEntity<>("This seller already contains this payment type", HttpStatus.FOUND);		
		} else {
			service.addSellerPaymentTypes(Long.valueOf(sellerId), paymentType);
			return new ResponseEntity<>("Payment " + paymentType.getName() + "succesfully added!", HttpStatus.OK);
		}
    }
}

