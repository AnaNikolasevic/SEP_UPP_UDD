package com.project.online_library.camundaServices.sendEmailDelegates;

import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.Editor;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.WriterRepository;
import com.project.online_library.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMailToWriter implements JavaDelegate {


    @Autowired
    EmailService emailService;

    @Autowired
    WriterRepository writerRepository;

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String username = (String) delegateExecution.getVariable("writer");
        Writer writer = writerRepository.findByUsername(username);
        String recipient = writer.getEmail();
        //String recipient = "milicaculibrk7@gmail.com";

        String title = (String)delegateExecution.getVariable("title");
        BookPrototype bookPrototype = bookPrototypeRepository.findByTitle(title);

        if(bookPrototype.getBookStatus().equals(BookStatus.INTERESTED)){
            this.sendInterested(writer, recipient, title);
        }else if(bookPrototype.getBookStatus().equals(BookStatus.DENIED)){
            this.sendDenied(writer, recipient, title);
        }else if(bookPrototype.getBookStatus().equals(BookStatus.ACCEPTED)) {
            this.sendAccepted(writer, recipient, title);
        }
    }

    public void sendInterested(Writer writer, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o plagijarizmu";
        String body = "Poštovani/a " +  writer.getFirstName()+
                ",\n\n Vaš inicijalni rukopis " + title + " " + "je prihvaćen, biće dalje pregledan.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }

    public void sendDenied(Writer writer, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o plagijarizmu";
        String body = "Poštovani/a " +  writer.getFirstName()+
                ",\n\n Vaš inicijalni rukopis " + title  + " " + "nije prihvaćen.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }

    public void sendAccepted(Writer writer, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o plagijarizmu";
        String body = "Poštovani/a " +  writer.getFirstName()+
                ",\n\n Vaša knjiga "  + title + " " + "je prošla test na pagijarizam i biće dalje pregledana.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }

    public void sendPlagiarism(Writer writer, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o plagijarizmu";
        String body = "Poštovani/a " +  writer.getFirstName()+
                ",\n\n Vaša knjiga "  + title + " " + "je pala test na pagijarizam.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }
}
