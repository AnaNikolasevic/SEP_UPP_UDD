package com.project.online_library.repository;

import com.project.online_library.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
}
