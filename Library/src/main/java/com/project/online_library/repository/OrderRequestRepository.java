package com.project.online_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.online_library.model.OrderRequest;


public interface OrderRequestRepository extends JpaRepository<OrderRequest,Long>{

}