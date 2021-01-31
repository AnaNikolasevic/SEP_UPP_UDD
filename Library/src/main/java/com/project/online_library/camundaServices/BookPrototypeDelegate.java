package com.project.online_library.camundaServices;


import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.Genre;
import com.project.online_library.model.Reader;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.GenreRepository;
import com.project.online_library.repository.WriterRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.sql.Wrapper;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookPrototypeDelegate implements JavaDelegate {


    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Autowired
    WriterRepository writerRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<FormSubmissionDto> form = (List<FormSubmissionDto>)delegateExecution.getVariable("form");
        System.out.println(form);
        BookPrototype bookPrototype = new BookPrototype();
        Genre genre = genreRepository.findByName(form.get(2).getFieldValue());
        Writer writer = writerRepository.findByUsername(form.get(3).getFieldValue());
        bookPrototype.setWriter(writer);
        bookPrototype.setGenre(genre);
        bookPrototype.setBookStatus(BookStatus.CREATED);
        bookPrototype.setSynopsis(form.get(1).getFieldValue());
        bookPrototype.setTitle(form.get(0).getFieldValue());
        bookPrototypeRepository.save(bookPrototype);
        delegateExecution.setVariable("writer", writer.getUsername());
    }
}
