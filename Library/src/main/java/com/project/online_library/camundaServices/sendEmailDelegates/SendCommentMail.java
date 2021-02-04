package com.project.online_library.camundaServices.sendEmailDelegates;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.repository.UserRepository;
import com.project.online_library.service.EmailService;

@Service
public class SendCommentMail implements JavaDelegate{
    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    	String writerUsername = (String)delegateExecution.getVariable("writer");
    	String comment = (String)delegateExecution.getVariable("comment");
        System.out.println(writerUsername);
        String recipient = userRepository.findByUsername(writerUsername).getEmail();
        String subject = "Komentari na vas rad";
        String body = "Poštovani " +
                ",\n\n Komentar je sledeci:\n " + comment;
        emailService.sendEmail(recipient, subject, body );
    }
}