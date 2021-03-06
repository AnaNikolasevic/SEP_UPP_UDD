package com.project.online_library.repository;

import com.project.online_library.model.Reader;
import com.project.online_library.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    Reader findByUsername(String username);

    Reader findByEmail(String email);
}
