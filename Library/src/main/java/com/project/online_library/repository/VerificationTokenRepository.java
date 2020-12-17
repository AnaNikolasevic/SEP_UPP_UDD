package com.project.online_library.repository;

import com.project.online_library.model.Customer;
import com.project.online_library.model.Users;
import com.project.online_library.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);


    VerificationToken findByUser(Users user);
}
