package com.project.online_library.service;

import com.project.online_library.dto.BookPrototypeDTO;
import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.repository.BookPrototypeRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookPrototypeService implements JavaDelegate {

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    public List<BookPrototypeDTO> getAllEditorsBookPrototypes(String username){

        List<BookPrototypeDTO> bookPrototypeDTOS = new ArrayList<>();
        List<BookPrototype> bookPrototypes = bookPrototypeRepository.findAll();
        for (BookPrototype bookPrototype : bookPrototypes) {
            if(bookPrototype.getEditor().getUsername().equals(username) && bookPrototype.getBookStatus().equals(BookStatus.CREATED)) {
                bookPrototypeDTOS.add(new BookPrototypeDTO(bookPrototype));
            }
        }
        return bookPrototypeDTOS;

    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String title = (String) delegateExecution.getVariable("title");
        BookPrototype bookPrototype = bookPrototypeRepository.findByTitle(title);
        bookPrototype.setPath((String) delegateExecution.getVariable("uploadFile"));
        bookPrototypeRepository.save(bookPrototype);

    }

}
