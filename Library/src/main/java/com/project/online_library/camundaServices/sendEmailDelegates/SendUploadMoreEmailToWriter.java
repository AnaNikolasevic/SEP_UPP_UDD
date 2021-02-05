package com.project.online_library.camundaServices.sendEmailDelegates;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.online_library.repository.UserRepository;
import com.project.online_library.repository.VerificationTokenRepository;
import com.project.online_library.repository.WriterRepository;
import com.project.online_library.service.EmailService;
import com.project.online_library.service.VerificationTokenService;
import org.springframework.stereotype.Service;

@Service
public class SendUploadMoreEmailToWriter implements JavaDelegate{
	@Autowired
    IdentityService identityService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    EmailService emailService;

    @Autowired
    WriterRepository writerRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    	String writerUsername = (String)delegateExecution.getVariable("writer");
        System.out.println(writerUsername);
        String recipient = writerRepository.findByUsername(writerUsername).getEmail();
        String subject = "Dodajte jos materijala";
        String body = "Poštovani " +
                ",\n\n Molimo vas da dodate jos materijala u pdf formatu kako bismo lakse odlucili da li ste pogodni za clanstvo.\n\n";
        emailService.sendEmail(recipient, subject, body );
    }
}