package com.project.online_library.camundaServices.sendEmailsDelegate;

import com.project.online_library.model.Editor;
import com.project.online_library.repository.EditorRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class SendEmailToEditor implements JavaDelegate {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long editorId = (Long) delegateExecution.getVariable("editorId");
        Editor editor = editorRepository.getOne(editorId);
    }
}
