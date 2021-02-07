package com.project.coingate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.coingate.model.Seller;
import com.project.coingate.repository.SellerRepository;
@Service
public class SellerService {
	@Autowired
	SellerRepository repository;
	
	Logger logger = LoggerFactory.getLogger(SellerService.class);
	
	public void addSeller(Seller seller) {
		// TODO Auto-generated method stub
		repository.save(seller);
		logger.info("Literary association with id " + seller.getId() + "added Bitcoin payment.");

	}

}
