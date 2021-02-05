package com.project.online_library.camundaServices.sendEmailDelegates;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.online_library.repository.UserRepository;
import com.project.online_library.repository.VerificationTokenRepository;
import com.project.online_library.service.EmailService;
import com.project.online_library.service.VerificationTokenService;
import org.springframework.stereotype.Service;

@Service
public class SendAcceptanceEmailToWriter implements JavaDelegate{
    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    	String writerUsername = (String)delegateExecution.getVariable("writer");
        System.out.println(writerUsername);
        String recipient = userRepository.findByUsername(writerUsername).getEmail();
        String subject = "Vase clanstvo je odobreno";
        String body = "Poštovani " +
                ",\n\n Vase clanstvo je odobreno. Molimo vas da izvrsite placanje svoje clanarine u roku od dve nedelje.\n\n";
        emailService.sendEmail(recipient, subject, body );
    }
}