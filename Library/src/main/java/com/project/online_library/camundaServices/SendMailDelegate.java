package com.project.online_library.camundaServices;

import com.project.online_library.model.Editor;
import com.project.online_library.repository.EditorRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMailDelegate implements JavaDelegate {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Object editor =  delegateExecution.getVariable("editorId");
        //Editor editor2 = (Editor) editor;
        System.out.println(editor);
      //  System.out.println(editor2.getEmail());

    }
}
