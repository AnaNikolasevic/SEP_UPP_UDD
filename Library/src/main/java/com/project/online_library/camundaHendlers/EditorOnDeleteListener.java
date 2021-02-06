package com.project.online_library.camundaHendlers;

import com.project.online_library.service.BetaReadersService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EditorOnDeleteListener implements TaskListener {

    @Autowired
    BetaReadersService betaReadersService;

    @Override
    public void notify(DelegateTask delegateTask) {


        String oneEditor = (String) delegateTask.getVariable("oneEditor");
        System.out.println(oneEditor + "++++++");

       // String editorsThatDidntComment = betaReadersService.checkPenaltyPoints(oneBetaReader);


        ArrayList<String> editorsThatDidntComment = (ArrayList<String>) delegateTask.getVariable("editorsThatDidntComment");
        editorsThatDidntComment.add(oneEditor);

        for (String editor : editorsThatDidntComment) {
            System.out.println(editor);
        }

        delegateTask.setVariable("editorsThatDidntComment", editorsThatDidntComment);

    }

}
