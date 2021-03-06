package com.project.online_library.camundaServices.sendEmailDelegates;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.model.Users;
import com.project.online_library.model.VerificationToken;
import com.project.online_library.repository.UserRepository;
import com.project.online_library.repository.VerificationTokenRepository;
import com.project.online_library.service.EmailService;
import com.project.online_library.service.VerificationTokenService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SendExpirationTokenEmail implements JavaDelegate {

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

        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)delegateExecution.getVariable("registration");
        System.out.println(registration);
        String recipient = null;
        for (FormSubmissionDto formField : registration) {
            if(formField.getId().equals("email")) {
                recipient = formField.getFieldValue().toString();
                System.out.println(recipient);
            }
        }
        String subject = "Nevalidan token";
        String body = "Poštovani " +
                ",\n\n Vaš token je istekao!\n\n";
        emailService.sendEmail(recipient, subject, body );
    }

}
