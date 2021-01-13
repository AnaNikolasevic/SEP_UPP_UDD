package com.project.seller_service.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.seller_service.model.PaymentType;
import com.project.seller_service.repository.PaymentTypeRepository;

@Service
public class PaymentTypeService {
	@Autowired
	private PaymentTypeRepository repository;
	
	public List<PaymentType> getAll() {
	    return repository.findAll();
	}



}
