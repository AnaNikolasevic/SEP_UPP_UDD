package com.project.seller_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.seller_service.model.PaymentType;
import com.project.seller_service.model.Seller;
import com.project.seller_service.repository.SellerRepository;

@Service
public class SellerService {
	@Autowired
	private SellerRepository repository;
	
	public List<Seller> getAll() {
	    return repository.findAll();
	}
	
	public Seller getOne(Long sellerId) {
	    return repository.getOne(sellerId);
	}

	public List<PaymentType> getSellerPaymentTypes(Long sellerId) {
		// TODO Auto-generated method stub
		return repository.getOne(sellerId).getPaymentTypes();
	}

	public List<PaymentType> addSellerPaymentTypes(Long sellerId, PaymentType paymentType) {
		// TODO Auto-generated method stub
		Seller seller = repository.getOne(sellerId);
		seller.getPaymentTypes().add(paymentType);
		repository.save(seller);
		return seller.getPaymentTypes();
	}

	public void checkIfExist(int sellerId) {
		// TODO Auto-generated method stub
		if(!repository.existsById(Long.valueOf(sellerId))) {
			Seller seller = new Seller();
			seller.setPaymentTypes(new ArrayList<PaymentType>());
			repository.save(seller);
		}
	}



}
