package com.project.seller_service.service;

import com.project.seller_service.model.Seller;
import com.project.seller_service.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.seller_service.dto.OrderRequestDto;
import com.project.seller_service.model.OrderRequest;
import com.project.seller_service.repository.OrderRequestRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.NoSuchElementException;


@Service
public class OrderRequestService {

	@Autowired
	OrderRequestRepository repository;
	@Autowired
	SellerRepository sellerRepository;


	public OrderRequest add(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setPrice(orderRequestDto.getPrice());
		orderRequest.setCurrency(orderRequestDto.getCurrency());
		orderRequest.setSellerId(orderRequestDto.getSellerId());
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
		orderRequest.setStatus("CREATED");
		orderRequest.setMerchant_timestamp(timeStamp);
		repository.save(orderRequest);

		return orderRequest;
	}

	public OrderRequest getOrderRequest(Long valueOf, String paymentType) {
		// TODO Auto-generated method stub
		OrderRequest orderRequest = repository.getOne(valueOf);
		if (orderRequest == null){
			throw new NoSuchElementException("there is no such order");
		}
		orderRequest.setPayment_type(paymentType);
		if (paymentType.equals("card")){
			Seller seller = sellerRepository.getOne(orderRequest.getSellerId());
			if (seller==null){
				throw new NoSuchElementException("there is no such order");
			}
			orderRequest.setMerchant_id(seller.getMerchant_id());
			orderRequest.setMerchant_password(seller.getMerchant_password());
			orderRequest.setError_url("");
			orderRequest.setFailed_url("");
			orderRequest.setSuccess_url("");
		}
		repository.save(orderRequest);
		return orderRequest;
	}

	public void setStatus (Long id, String status){

		OrderRequest orderRequest = repository.findById(id).orElse(null);
		if (orderRequest == null ){
			if (orderRequest == null){
				throw new NoSuchElementException("there is no such order");
			}
		}
		orderRequest.setStatus(status);
		repository.save(orderRequest);
	}

	public void changeOrderRequestStatus(Long id, String status){

		OrderRequest orderRequest = repository.getOne(id);
		orderRequest.setStatus(status);
	  repository.save(orderRequest);

	}
	
}
