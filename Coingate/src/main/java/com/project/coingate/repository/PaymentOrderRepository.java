package com.project.coingate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.coingate.model.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

}
