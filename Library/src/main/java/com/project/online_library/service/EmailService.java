package com.project.online_library.service;

import com.project.online_library.model.Customer;
import com.project.online_library.model.Users;
import com.project.online_library.model.VerificationToken;
import com.project.online_library.repository.VerificationTokenRepository;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    //genericko slanje email-a
    @Async
    public void sendEmail(String recipient, String subject, String body) throws MailException, InterruptedException {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipient);
        email.setFrom(environment.getProperty("spring.mail.username"));
        email.setSubject(subject);
        email.setText(body);
        javaMailSender.send(email);

    }


}
