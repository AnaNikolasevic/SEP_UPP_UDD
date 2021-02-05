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
public class SendExpirationPaymentToWriter implements JavaDelegate{
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

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    	String writerUsername = (String)delegateExecution.getVariable("writer");
        System.out.println(writerUsername);
        String recipient = userRepository.findByUsername(writerUsername).getEmail();
        String subject = "Istekao rok za placanje";
        String body = "Poštovani " +
                ",\n\n Vas rok za placanje clanarine je istekao. Zahtev za registraciju je odbijen\n\n";
        emailService.sendEmail(recipient, subject, body );
    }
}
