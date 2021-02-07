package com.project.coingate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.coingate.model.PaymentOrder;
import com.project.coingate.model.PaymentOrderStatus;
import com.project.coingate.repository.PaymentOrderRepository;

@Service
public class PaymentOrderService {
	
	@Autowired
	PaymentOrderRepository paymentOrderRepository;
	
	Logger logger = LoggerFactory.getLogger(PaymentOrderService.class);
	
	public void success(String paymentOrderId) {
		// TODO Auto-generated method stub
		PaymentOrder paymentOrder = paymentOrderRepository.getOne( Long.parseLong(paymentOrderId));
		paymentOrder.setStatus(PaymentOrderStatus.PAID);
		paymentOrderRepository.save(paymentOrder);
        logger.info("[BITCOIN] payment request with id: " + paymentOrder.getId() + " updated status to: " + paymentOrder.getStatus());

	}

	public void error(String paymentOrderId) {
		// TODO Auto-generated method stub
		PaymentOrder paymentOrder = paymentOrderRepository.getOne( Long.parseLong(paymentOrderId));
		paymentOrder.setStatus(PaymentOrderStatus.CANCELED);
		paymentOrderRepository.save(paymentOrder);
        logger.info("[BITCOIN] payment request with id: " + paymentOrder.getId() + " updated status to: " + paymentOrder.getStatus());

	}

}
