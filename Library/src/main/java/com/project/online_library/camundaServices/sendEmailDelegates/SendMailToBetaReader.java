package com.project.online_library.camundaServices.sendEmailDelegates;

import camundajar.impl.scala.Console;
import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BetaReader;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.Reader;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.BetaReaderRepository;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.ReaderRepository;
import com.project.online_library.repository.WriterRepository;
import com.project.online_library.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SendMailToBetaReader implements JavaDelegate {

    @Autowired
    EmailService emailService;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BetaReaderRepository betaReaderRepository;

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        ArrayList<String> punishedBetaReaders = (ArrayList<String>) delegateExecution.getVariable("punishedBetaReaders");
        ArrayList<String> selectedBetaReaders = (ArrayList<String>) delegateExecution.getVariable("betaReadersUsernameList");


        for(String pbr: punishedBetaReaders){
            System.out.println(pbr);
        }

        String title = (String)delegateExecution.getVariable("title");
        BookPrototype bookPrototype = bookPrototypeRepository.findByTitle(title);

        if(bookPrototype.getBookStatus().equals(BookStatus.INTERESTED)) {
            for(String sbr: selectedBetaReaders){
                BetaReader betaReader = betaReaderRepository.findByUsername(sbr);
                this.notifyBetaReaders(betaReader, betaReader.getEmail(), title);
            }
        }

        if(bookPrototype.getBookStatus().equals(BookStatus.COMMENTED)) {
            for(String punishedBetaReaderEmail: punishedBetaReaders){
                Reader reader = readerRepository.findByEmail(punishedBetaReaderEmail);
                this.sendBetaStatusLoss(reader, punishedBetaReaderEmail);
            }
        }

    }

    public void notifyBetaReaders(BetaReader betaReader, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o novoj knjizi za komentarianje";
        String body = "Poštovani/a " +  betaReader.getFirstName()+
                ",\n\n Prispela je nova knjiga,  " + title + " " + "za koju treba da ostavite komentar najkasnije u naredna 3 dana.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }

    public void sendBetaStatusLoss(Reader reader, String recipient) throws InterruptedException {
        String subject = "Obaveštenje o statusu";
        String body = "Poštovani/a " +  reader.getFirstName()+
                ",\n\n Izgubili ste status beta-čitaoca jer ste sakupili 5 kaznenih poena! \n\n" +
                ",\n\n Sada imate status običnog čitaoca! \n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }
}
