package com.project.online_library.camundaHendlers;

import com.project.online_library.repository.BetaReaderRepository;
import com.project.online_library.service.BetaReadersService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddCommentOnDeleteListener implements TaskListener {

    @Autowired
    BetaReadersService betaReadersService;

    @Override
    public void notify(DelegateTask delegateTask) {


        String oneBetaReader = (String) delegateTask.getVariable("oneBetaReader");
        System.out.println(oneBetaReader + "++++++");

        String punishedBetaReader = betaReadersService.checkPenaltyPoints(oneBetaReader);

        /*
        ArrayList<String> betaReadersThatCommented = (ArrayList<String>) delegateTask.getVariable("betaReadersThatCommented");
        betaReadersThatCommented.add(oneBetaReader);

        for(String betaReader: betaReadersThatCommented){
            System.out.println(betaReader);
        }

        delegateTask.setVariable("betaReadersThatCommented", betaReadersThatCommented);
        */

        if(punishedBetaReader != null){
            ArrayList<String> punishedBetaReaders = (ArrayList<String>) delegateTask.getVariable("punishedBetaReaders");
            punishedBetaReaders.add(punishedBetaReader);
            for(String betaReader: punishedBetaReaders){
                System.out.println(betaReader);
            }
            delegateTask.setVariable("punishedBetaReaders", punishedBetaReaders);

        }

        String comment = (String) delegateTask.getVariable("comment");


        ArrayList<String> comments = (ArrayList<String>) delegateTask.getVariable("comments");
        comments.add(comment);
        for(String c: comments){
            System.out.println(c);
        }
        delegateTask.setVariable("comments", comments);
    }

}
