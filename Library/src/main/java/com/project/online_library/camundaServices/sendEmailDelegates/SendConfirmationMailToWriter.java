package com.project.online_library.camundaServices.sendEmailDelegates;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendConfirmationMailToWriter implements JavaDelegate {

    @Autowired
    EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
/*
        List<FormSubmissionDto> form = (List<FormSubmissionDto>)delegateExecution.getVariable("form");
        System.out.println(form);
        String recipient = null;
        for (FormSubmissionDto formField : form) {
            if(formField.getId().equals("email")) {
                recipient = formField.getFieldValue();
                System.out.println(recipient);
            }
        }
        String subject = "Nevalidan token";
        String body = "Poštovani " +
                ",\n\n Vaš token je istekao!\n\n";
        emailService.sendEmail(recipient, subject, body );*/
    }
}
