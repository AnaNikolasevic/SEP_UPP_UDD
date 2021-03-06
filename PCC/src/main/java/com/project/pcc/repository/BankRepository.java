package com.project.pcc.repository;

import com.project.pcc.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByPanID(String panID);
}
