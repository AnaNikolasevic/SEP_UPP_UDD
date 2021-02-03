package com.project.online_library.camundaServices;

import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.repository.BookPrototypeRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GeneratePotentialPlagiarism implements JavaDelegate {

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<BookPrototype> bookPrototypeList = bookPrototypeRepository.findByBookStatus(BookStatus.APPROVED);
        int numberOfElements = 2 ;
        List<BookPrototype> plagiarism = getRandomElement(bookPrototypeList, numberOfElements);
        String s = "Potential plagiarism: " +  plagiarism.get(0).getTitle() + ", " + plagiarism.get(1).getTitle();
        System.out.println("Ovdee idee natpis o plagijatima: " + s);
        delegateExecution.setVariable("plagiarism", s);

    }

    public List<BookPrototype> getRandomElement(List<BookPrototype> list, int totalItems)
    {
        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        List<BookPrototype> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {

            // take a raundom index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(list.get(randomIndex));

            // Remove selected element from orginal list
            list.remove(randomIndex);
        }
        return newList;
    }
}


