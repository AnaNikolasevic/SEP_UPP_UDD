package com.project.online_library.service;

import com.project.online_library.dto.BookPrototypeDTO;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookPrototypeService {

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    public List<BookPrototypeDTO> getAllEditorsBookPrototypes(String username){

        List<BookPrototypeDTO> bookPrototypeDTOS = new ArrayList<>();
        List<BookPrototype> bookPrototypes = bookPrototypeRepository.findAll();
        for (BookPrototype bookPrototype : bookPrototypes) {
            if(bookPrototype.getEditor().getUsername().equals(username)) {
                bookPrototypeDTOS.add(new BookPrototypeDTO(bookPrototype));
            }
        }
        return bookPrototypeDTOS;

    }
}
