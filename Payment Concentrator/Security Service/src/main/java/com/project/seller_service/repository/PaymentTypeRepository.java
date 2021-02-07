package com.project.seller_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.seller_service.model.PaymentType;


public interface PaymentTypeRepository extends JpaRepository<PaymentType,Long>{

    PaymentType findByName(String name);

}
