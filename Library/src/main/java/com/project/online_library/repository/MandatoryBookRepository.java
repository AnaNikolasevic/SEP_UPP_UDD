package com.project.online_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.online_library.model.MandatoryBook;

public interface MandatoryBookRepository extends JpaRepository<MandatoryBook, Long> {
}

