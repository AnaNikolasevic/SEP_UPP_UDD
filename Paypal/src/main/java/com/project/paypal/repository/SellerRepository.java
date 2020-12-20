package com.project.paypal.repository;

import com.project.paypal.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    public Seller findOneById(long id);

}