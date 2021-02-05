package com.project.online_library.repository;


import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BookPrototype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookPrototypeRepository extends JpaRepository<BookPrototype, Long> {

    BookPrototype findByTitle(String title);

    List<BookPrototype> findByBookStatus(BookStatus bookStatus);

}
