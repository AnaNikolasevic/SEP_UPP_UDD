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

    @Async
    public void sendEmail(Users user) throws MailException, InterruptedException {

        VerificationToken verificationToken = verificationTokenRepository.findByUser(user);

        if (verificationToken != null){
            String token = verificationToken.getToken();
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setFrom(environment.getProperty("spring.mail.username"));
            email.setSubject("Potvrda registracije");
            email.setText("Dobrodošli " + user.getFirstName() +
                    ",\n\n Da biste potvrdili vašu email adresu molimo Vas da pristupite sledećem linku:\n\n" +
                    "http://localhost:8081/activationForm/" + token +
                    "\n\n Nalog možete potvrditi u naredna 24h\n\n" +
                    "\n\nHvala Vam na ukazanom poverenju!\n\n\n\n" );
            javaMailSender.send(email);
        }
    }

}
