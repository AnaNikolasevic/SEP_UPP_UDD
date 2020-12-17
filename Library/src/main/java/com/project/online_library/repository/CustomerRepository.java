package com.project.online_library.repository;

import com.project.online_library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByEmailIgnoreCase(String emailId);
}
