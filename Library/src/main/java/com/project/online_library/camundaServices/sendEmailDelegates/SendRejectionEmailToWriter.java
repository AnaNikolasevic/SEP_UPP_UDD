package com.project.online_library.camundaServices.sendEmailDelegates;

import java.util.List;

import com.project.online_library.repository.WriterRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.repository.UserRepository;
import com.project.online_library.repository.VerificationTokenRepository;
import com.project.online_library.service.EmailService;
import com.project.online_library.service.VerificationTokenService;
import org.springframework.stereotype.Service;

@Service
public class SendRejectionEmailToWriter implements JavaDelegate{
	@Autowired
    IdentityService identityService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WriterRepository writerRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    	String writerUsername = (String)delegateExecution.getVariable("writer");
        System.out.println(writerUsername);
        String recipient = userRepository.findByUsername(writerUsername).getEmail();
        String subject = "Odbijena registracija";
        String body = "Poštovani " +
                ",\n\n Nasi clanovi odbora su odlucili da niste podobni za clanstvo.\n\n";
        emailService.sendEmail(recipient, subject, body );

        writerRepository.delete(writerRepository.getByUsername(writerUsername));

    }
}
