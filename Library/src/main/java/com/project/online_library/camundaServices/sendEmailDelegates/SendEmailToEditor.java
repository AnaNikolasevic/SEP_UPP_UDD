package com.project.online_library.camundaServices.sendEmailDelegates;

import com.project.online_library.model.Editor;
import com.project.online_library.repository.EditorRepository;
import com.project.online_library.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendEmailToEditor implements JavaDelegate {

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long editorId = (Long) delegateExecution.getVariable("editorId");
        Editor editor = editorRepository.getOne(editorId);
        String recipient = editor.getEmail();
        //String recipient = "masa.matovic97@gmail.com";
        String subject = "Nova prijava knjige";
        String body = "Poštovani/a " + editor.getFirstName()+
                ",\n\n U sistem je dodata nova prijava knjige, koju možete videti na svom profilu.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }
}
