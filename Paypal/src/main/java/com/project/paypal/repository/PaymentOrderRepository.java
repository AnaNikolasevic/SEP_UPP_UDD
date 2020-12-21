package com.project.paypal.repository;

import com.project.paypal.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

    PaymentOrder findOneByPaymentId(String id);

    PaymentOrder findOneById(String id);

}
