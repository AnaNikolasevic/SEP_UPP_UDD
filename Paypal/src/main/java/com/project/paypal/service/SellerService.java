package com.project.paypal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.paypal.model.Seller;
import com.project.paypal.repository.SellerRepository;
@Service
public class SellerService {
	@Autowired
	SellerRepository repository;

	public void addSeller(Seller seller) {
		// TODO Auto-generated method stub
		repository.save(seller);
	}

}
