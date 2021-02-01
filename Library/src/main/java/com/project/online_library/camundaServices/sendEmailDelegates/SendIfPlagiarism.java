package com.project.online_library.camundaServices.sendEmailDelegates;

import com.project.online_library.model.Editor;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.WriterRepository;
import com.project.online_library.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendIfPlagiarism implements JavaDelegate {


    @Autowired
    EmailService emailService;

    @Autowired
    WriterRepository writerRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String username = (String) delegateExecution.getVariable("writer");
        Writer writer = writerRepository.findByUsername(username);
       // String recipient = writer.getEmail();
        String recipient = "masa.matovic97@gmail.com";
        String subject = "Obaveštenje o plagijarizmu";
        String body = "Poštovani/a " +  writer.getFirstName()+
                ",\n\n Vaša knjiga je pala test na pagijarizam\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }
}
