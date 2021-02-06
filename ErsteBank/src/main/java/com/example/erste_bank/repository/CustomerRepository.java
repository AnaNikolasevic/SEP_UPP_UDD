package com.example.erste_bank.repository;


import com.example.erste_bank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {



    Customer findByMerchantId(String merchant_id);
    Customer findByPAN(String merchant_id);


}
