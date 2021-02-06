package com.project.online_library.camundaServices.sendEmailDelegates;

import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.BetaReader;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.Editor;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.EditorRepository;
import com.project.online_library.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;

@Service
public class SendEmailToEditor implements JavaDelegate {

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String title = (String)delegateExecution.getVariable("title");
        BookPrototype bookPrototype = bookPrototypeRepository.findByTitle(title);
        Editor mainEditor = bookPrototype.getEditor();

        ArrayList<String> editorsUsernameList = (ArrayList<String>) delegateExecution.getVariable("editorsUsernameList");
        ArrayList<String> editorsThatDidntComment = (ArrayList<String>) delegateExecution.getVariable("editorsThatDidntComment");
        ArrayList<String> replacementEditorsList = (ArrayList<String>) delegateExecution.getVariable("replacementEditorsList");


        delegateExecution.setVariable("mainEditor", mainEditor.getUsername());

        String recipient = null;
        Editor editor = null;


        String editorId = (String) delegateExecution.getVariable("editorId");
        if(editorId != null){
            editor = editorRepository.findByUsername(editorId);
            recipient = editor.getEmail();
        }


        if(bookPrototype.getBookStatus().equals(BookStatus.APPROVED)) {
            if(replacementEditorsList != null) {
                for (String sbr : replacementEditorsList) {
                    Editor editor1 = editorRepository.findByUsername(sbr);
                    this.notifyAllEditors(editor1, editor1.getEmail(), title);
                }
            }else  if(editorsThatDidntComment != null) {
                this.notifyMainEditorAboutReplacement(mainEditor, mainEditor.getEmail(), title);
            }else if (editorsUsernameList != null) {
                for (String sbr : editorsUsernameList) {
                    Editor editor1 = editorRepository.findByUsername(sbr);
                    this.notifyAllEditors(editor1, editor1.getEmail(), title);
                }
            } else {
                this.sendPlagiarismReport(mainEditor, mainEditor.getEmail(), title);
            }
        }

        if(bookPrototype.getBookStatus().equals(BookStatus.CREATED) && editor != null){
            this.newBookInSystem(editor, recipient, title);
        }

    }

    public void sendPlagiarismReport(Editor editor, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o prijavi plagijarizma";
        String body = "Poštovani/a " +  editor.getFirstName()+
                ",\n\n Priložena je žalba da je  " + title + " " + " plagijat.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }

    public void newBookInSystem(Editor editor, String recipient, String title) throws InterruptedException {
        String subject = "Nova prijava knjige";
        String body = "Poštovani/a " + editor.getFirstName()+
                ",\n\n U sistem je dodata nova prijava knjige, koju možete videti na svom profilu.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }

    public void notifyAllEditors(Editor editor, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o prijavi plagijarizma";
        String body = "Poštovani/a " + editor.getFirstName()+
                ",\n\n Pristigle su Vam nove knjige za proveru na plagijarizam.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }

    public void notifyMainEditorAboutReplacement(Editor editor, String recipient, String title) throws InterruptedException {
        String subject = "Obaveštenje o zameni urednika";
        String body = "Poštovani/a " + editor.getFirstName()+
                ",\n\n Nisu svi urednici završili komentarisanje na vreme, izvršite zamenu.\n\n" +
                "\n\n Srdačan pozdrav!\n\n";
        emailService.sendEmail(recipient, subject, body);
    }
}
