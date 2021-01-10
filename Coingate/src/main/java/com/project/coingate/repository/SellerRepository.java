package com.project.coingate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.coingate.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

	Seller findOneById(Long sellerId);

}
