package com.project.online_library.camundaServices;

import com.project.online_library.dto.FormSubmissionDto;
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
public class BookStatusDelegate implements JavaDelegate {

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String title = (String)delegateExecution.getVariable("title");

        BookPrototype bookPrototype = bookPrototypeRepository.findByTitle(title);

        Boolean isInterestedInBook = null;
        Boolean isPlagiarism = null;
        ArrayList<String> betaReadersUsernameList = null;
        ArrayList<String> punishedBetaReaders = null;

        if((Boolean)delegateExecution.getVariable("isInterestedInBook") != null){
            isInterestedInBook = (Boolean)delegateExecution.getVariable("isInterestedInBook");
        }
        if((Boolean)delegateExecution.getVariable("isPlagiarism") != null){
            isPlagiarism = (Boolean)delegateExecution.getVariable("isPlagiarism");
        }
        if((ArrayList<String>)delegateExecution.getVariable("betaReadersUsernameList") != null){
            betaReadersUsernameList = (ArrayList<String>)delegateExecution.getVariable("betaReadersUsernameList");
        }
        if((ArrayList<String>)delegateExecution.getVariable("punishedBetaReaders") != null){
            punishedBetaReaders = (ArrayList<String>)delegateExecution.getVariable("punishedBetaReaders");
        }


        if(isInterestedInBook != null && isInterestedInBook && bookPrototype.getBookStatus().equals(BookStatus.CREATED)){
            bookPrototype.setBookStatus(BookStatus.INTERESTED);
            System.out.println(bookPrototype.getBookStatus() + "---------------");
        }else if(isInterestedInBook != null && !isInterestedInBook && bookPrototype.getBookStatus().equals(BookStatus.CREATED)){
            bookPrototype.setBookStatus(BookStatus.DENIED);
            System.out.println(bookPrototype.getBookStatus() + "---------------");
        }else if(isPlagiarism != null && isPlagiarism && bookPrototype.getBookStatus().equals(BookStatus.INTERESTED)){
            bookPrototype.setBookStatus(BookStatus.PLAGIARISM);
            System.out.println(bookPrototype.getBookStatus() + "---------------");
        }else if(betaReadersUsernameList != null && bookPrototype.getBookStatus().equals(BookStatus.INTERESTED)) {
            bookPrototype.setBookStatus(BookStatus.COMMENTED);
            System.out.println(bookPrototype.getBookStatus() + "---------------");
        }

        bookPrototypeRepository.save(bookPrototype);
    }
}
