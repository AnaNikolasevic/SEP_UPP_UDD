package com.project.seller_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.seller_service.model.PaymentType;
import com.project.seller_service.model.Seller;
import com.project.seller_service.service.PaymentTypeService;
import com.project.seller_service.service.SellerService;

@CrossOrigin
@RestController
@RequestMapping("/paymentType")
public class PaymentTypeController {
	@Autowired
	PaymentTypeService service;
	
	@GetMapping("/paymentTypes")
    public ResponseEntity<?> getPaymentTypes() {
        return new ResponseEntity<List<PaymentType>>(service.getAll(), HttpStatus.OK);
    }
}
