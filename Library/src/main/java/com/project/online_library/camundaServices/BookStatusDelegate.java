package com.project.online_library.camundaServices;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.repository.BookPrototypeRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStatusDelegate implements JavaDelegate {

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String title = (String)delegateExecution.getVariable("title");

        BookPrototype bookPrototype = bookPrototypeRepository.findByTitle(title);

        boolean isInterestedInBook = (boolean)delegateExecution.getVariable("isInterestedInBook");

        if(isInterestedInBook){
            bookPrototype.setBookStatus(BookStatus.INTERESTED);
            System.out.println(bookPrototype.getBookStatus() + "---------------");
        }else{
            bookPrototype.setBookStatus(BookStatus.DENIED);
            System.out.println(bookPrototype.getBookStatus() + "---------------");
        }


    }
}
