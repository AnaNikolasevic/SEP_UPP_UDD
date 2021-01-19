package com.project.online_library.repository;

import com.project.online_library.model.Lector;
import com.project.online_library.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, Long> {

    Lector findByUsername(String username);
}
