package com.project.online_library.repository;

import com.project.online_library.model.BetaReader;
import com.project.online_library.model.Users;
import org.glassfish.jersey.Beta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetaReaderRepository extends JpaRepository<BetaReader, Long> {

    BetaReader findByUsername(String username);
}
