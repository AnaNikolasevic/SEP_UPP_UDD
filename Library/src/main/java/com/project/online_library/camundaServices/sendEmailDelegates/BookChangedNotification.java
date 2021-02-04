package com.project.online_library.camundaServices.sendEmailDelegates;


import com.project.online_library.model.Writer;
import com.project.online_library.repository.WriterRepository;
import com.project.online_library.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookChangedNotification implements JavaDelegate {

    @Autowired
    WriterRepository writerRepository;

    @Autowired
    EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("writer");
        Writer writer = writerRepository.findByUsername(username);
        String recipient = writer.getEmail();
        String title = (String)delegateExecution.getVariable("title");

        String subject = "Lektorisanje knjige";
        String body = "Poštovani/a " +  writer.getFirstName()+
                ",\n\n Vaš inicijalni rukopis " + title + " " + "je lektorisan, možete izvršiti promene.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);

        delegateExecution.setVariable("fileView", (String) delegateExecution.getVariable("uploadFile"));


    }
}
