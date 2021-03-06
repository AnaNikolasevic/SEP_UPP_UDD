package com.project.aik_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.aik_bank.model.Customer;
import com.project.aik_bank.service.SellerService;


@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {
	@Autowired
	SellerService service;
    
    @PostMapping(path= "")
    public ResponseEntity<?> addSeller(@RequestBody Customer seller){
    	try {
    		service.addSeller(seller);
            return new ResponseEntity<>("successfully", HttpStatus.OK);
    	} catch (Exception e) {
			// TODO: handle exception
    		return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
		}
    }
}
