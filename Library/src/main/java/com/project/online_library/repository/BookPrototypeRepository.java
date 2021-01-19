package com.project.online_library.repository;

import com.project.online_library.model.BookPrototype;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPrototypeRepository extends JpaRepository<BookPrototype, Long> {

    BookPrototype findByTitle(String title);

}
