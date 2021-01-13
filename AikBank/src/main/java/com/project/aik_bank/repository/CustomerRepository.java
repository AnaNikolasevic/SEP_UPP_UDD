package com.project.aik_bank.repository;

import com.project.aik_bank.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {



    Customer findByMerchantId(String merchant_id);
    Customer findByPAN(String merchant_id);


}
