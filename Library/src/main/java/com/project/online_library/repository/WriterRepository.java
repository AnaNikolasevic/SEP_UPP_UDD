package com.project.online_library.repository;

import com.project.online_library.model.Reader;
import com.project.online_library.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long> {
}
