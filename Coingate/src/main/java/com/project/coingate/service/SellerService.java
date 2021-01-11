package com.project.coingate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.coingate.model.Seller;
import com.project.coingate.repository.SellerRepository;
@Service
public class SellerService {
	@Autowired
	SellerRepository repository;

	public void addSeller(Seller seller) {
		// TODO Auto-generated method stub
		repository.save(seller);
	}

}
