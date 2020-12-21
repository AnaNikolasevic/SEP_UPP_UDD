package com.project.online_library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.dto.OrderRequestDto;
import com.project.online_library.model.OrderRequest;
import com.project.online_library.repository.OrderRequestRepository;

@Service
public class OrderRequestService {
	@Autowired
	OrderRequestRepository repository;

	public OrderRequest add(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setPrice(orderRequestDto.getPrice());
		orderRequest.setCurrency(orderRequestDto.getCurrency());
		orderRequest.setSellerId(orderRequestDto.getSelerId());
		repository.save(orderRequest);
		return orderRequest;
	}

	public OrderRequest getOrderRequest(Long valueOf) {
		// TODO Auto-generated method stub
		return repository.getOne(valueOf);
	}
	
	
}
