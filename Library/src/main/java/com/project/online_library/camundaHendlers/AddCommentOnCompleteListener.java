package com.project.online_library.camundaHendlers;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddCommentOnCompleteListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        String comment = (String) delegateTask.getVariable("comment");


            ArrayList<String> comments = (ArrayList<String>) delegateTask.getVariable("comments");
            comments.add(comment);
            for(String c: comments){
                System.out.println(c);
            }
            delegateTask.setVariable("comments", comments);
    }
}
