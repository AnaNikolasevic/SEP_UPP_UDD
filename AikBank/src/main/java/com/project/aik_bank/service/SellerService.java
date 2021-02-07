package com.project.aik_bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.aik_bank.model.Customer;
import com.project.aik_bank.repository.CustomerRepository;

@Service
public class SellerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public void addSeller(Customer seller) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findByPAN(seller.getPAN());
		customer.setMerchantId(seller.getMerchantId());
		customer.setPassword(seller.getPassword());
		customerRepository.save(customer);
	}

}
