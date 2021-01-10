package com.project.seller_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.seller_service.dto.OrderRequestDto;
import com.project.seller_service.model.OrderRequest;
import com.project.seller_service.repository.OrderRequestRepository;

@Service
public class OrderRequestService {
	@Autowired
	OrderRequestRepository repository;

	public OrderRequest add(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setPrice(orderRequestDto.getPrice());
		orderRequest.setCurrency(orderRequestDto.getCurrency());
		orderRequest.setSellerId(orderRequestDto.getSellerId());
		repository.save(orderRequest);
		return orderRequest;
	}

	public OrderRequest getOrderRequest(Long valueOf) {
		// TODO Auto-generated method stub
		return repository.getOne(valueOf);
	}
	
	
}
