package com.project.online_library.camundaServices;


import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.Genre;
import com.project.online_library.model.Reader;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.GenreRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookPrototypeDelegate implements JavaDelegate {


    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<FormSubmissionDto> form = (List<FormSubmissionDto>)delegateExecution.getVariable("form");
        System.out.println(form);
        BookPrototype bookPrototype = new BookPrototype();
        Genre genre = genreRepository.findByName(form.get(2).getFieldValue());
        bookPrototype.setGenre(genre);
        bookPrototype.setBookStatus(BookStatus.CREATED);
        bookPrototype.setSynopsis(form.get(1).getFieldValue());
        bookPrototype.setTitle(form.get(0).getFieldValue());
        bookPrototypeRepository.save(bookPrototype);
    }
}
