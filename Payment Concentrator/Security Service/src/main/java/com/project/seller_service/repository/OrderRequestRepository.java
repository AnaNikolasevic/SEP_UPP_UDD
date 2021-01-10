package com.project.seller_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.seller_service.model.OrderRequest;


public interface OrderRequestRepository extends JpaRepository<OrderRequest,Long>{

}