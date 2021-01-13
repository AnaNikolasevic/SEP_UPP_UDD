package com.project.seller_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.seller_service.dto.OrderRequestDto;
import com.project.seller_service.model.OrderRequest;
import com.project.seller_service.repository.OrderRequestRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderRequestService {

	@Autowired
	OrderRequestRepository orderRequestRepository;


	public OrderRequest add(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setPrice(orderRequestDto.getPrice());
		orderRequest.setCurrency(orderRequestDto.getCurrency());
		orderRequest.setSellerId(orderRequestDto.getSellerId());
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
		orderRequest.setMerchant_timestamp(timeStamp);
		orderRequestRepository.save(orderRequest);
		return orderRequest;
	}

	public OrderRequest getOrderRequest(Long valueOf) {
		// TODO Auto-generated method stub
		return orderRequestRepository.getOne(valueOf);
	}

	public void changeOrderRequestStatus(Long id, String status){

		OrderRequest orderRequest = orderRequestRepository.getOne(id);
		orderRequest.setStatus(status);
		orderRequestRepository.save(orderRequest);

	}
	
	
}
