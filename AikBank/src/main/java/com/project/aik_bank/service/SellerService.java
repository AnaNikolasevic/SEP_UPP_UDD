package com.project.aik_bank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.aik_bank.model.Customer;
import com.project.aik_bank.repository.CustomerRepository;

@Service
public class SellerService {

	@Autowired
	CustomerRepository customerRepository;
	
	Logger logger = LoggerFactory.getLogger(SellerService.class);
	
	public void addSeller(Customer seller) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findByPAN(seller.getPAN());
		customer.setMerchantId(seller.getMerchantId());
		customer.setPassword(seller.getPassword());
		customerRepository.save(customer);
		logger.info("Literary association with id " + seller.getId() + "added Card payment.");
	}

}
