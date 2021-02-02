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

        BookPrototype bookPrototype = new BookPrototype();
        Genre genre = genreRepository.findByName((String) delegateExecution.getVariable("genre"));
        Writer writer = writerRepository.findByUsername((String) delegateExecution.getVariable("writer"));
        bookPrototype.setWriter(writer);
        bookPrototype.setGenre(genre);
        bookPrototype.setBookStatus(BookStatus.CREATED);
        bookPrototype.setSynopsis((String) delegateExecution.getVariable("synopsis"));
        bookPrototype.setTitle((String) delegateExecution.getVariable("title"));
        bookPrototypeRepository.save(bookPrototype);
       // delegateExecution.setVariable("writer", writer.getUsername());

    }
}
