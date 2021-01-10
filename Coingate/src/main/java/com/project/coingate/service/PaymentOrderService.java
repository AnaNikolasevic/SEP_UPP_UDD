package com.project.coingate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.coingate.model.PaymentOrder;
import com.project.coingate.model.PaymentOrderStatus;
import com.project.coingate.repository.PaymentOrderRepository;

@Service
public class PaymentOrderService {
	
	@Autowired
	PaymentOrderRepository paymentOrderRepository;
	
	public void success(String paymentOrderId) {
		// TODO Auto-generated method stub
		PaymentOrder paymentOrder = paymentOrderRepository.getOne( Long.parseLong(paymentOrderId));
		paymentOrder.setStatus(PaymentOrderStatus.PAID);
		paymentOrderRepository.save(paymentOrder);
	}

	public void error(String paymentOrderId) {
		// TODO Auto-generated method stub
		PaymentOrder paymentOrder = paymentOrderRepository.getOne( Long.parseLong(paymentOrderId));
		paymentOrder.setStatus(PaymentOrderStatus.CANCELED);
		paymentOrderRepository.save(paymentOrder);
	}

}
