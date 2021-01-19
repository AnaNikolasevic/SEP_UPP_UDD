package com.project.online_library.camundaServices;

import com.project.online_library.model.Editor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SendMailDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Editor editor = (Editor) delegateExecution.getVariable("editor");
        System.out.println(editor.getId());
        System.out.println(editor.getEmail());

    }
}
