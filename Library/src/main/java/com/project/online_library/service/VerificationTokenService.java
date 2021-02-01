package com.project.online_library.service;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.model.Customer;
import com.project.online_library.model.Users;
import com.project.online_library.model.VerificationToken;
import com.project.online_library.repository.UserRepository;
import com.project.online_library.repository.VerificationTokenRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenService implements JavaDelegate {

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



    //kreiranje tokena i slanja emaila sa verifikacionim linkom
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)delegateExecution.getVariable("registration");
        System.out.println(registration);
        String username = null;
        for (FormSubmissionDto formField : registration) {
            if(formField.getId().equals("username")) {
                username = formField.getFieldValue().toString();
            }
        }

        Optional<Users> saved = Optional.of(userRepository.findByUsername(username));

        // Pravljenje i cuvanje tokena ako je korisnik sacuvan
        saved.ifPresent( u -> {
            try {
                String token = UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(), token);

                // salanje  verifikacionog mejla
                String recipient = u.getEmail();
                String subject = "Potvrda registracije";
                String body = "Dobrodošli " + u.getFirstName() +
                        ",\n\n Da biste potvrdili vašu email adresu molimo Vas da pristupite sledećem linku:\n\n" +
                        "http://localhost:8084/activationForm/" + token +
                        "\n\n Nalog možete potvrditi u naredna 24h\n\n" +
                        "\n\nHvala Vam na ukazanom poverenju!\n\n\n\n";
                emailService.sendEmail(recipient, subject, body);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }


    public void  save (Users user, String token){
        VerificationToken verificationToken = new VerificationToken(user, token);
        //podesavanje isticanja tokena za 24 sata u minutima
        verificationToken.setExpiryDate(calculateExpiryDate(24*60));
        verificationTokenRepository.save(verificationToken);
    }

    public Timestamp calculateExpiryDate (int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }

    public VerificationToken findByToken (String token){
        return verificationTokenRepository.findByToken(token);
    }


}
