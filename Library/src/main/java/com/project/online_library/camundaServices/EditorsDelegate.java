package com.project.online_library.camundaServices;

import com.project.online_library.model.BookPrototype;
import com.project.online_library.repository.BookPrototypeRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;

@Service
public class EditorsDelegate implements JavaDelegate {

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String title = ((String) delegateExecution.getVariable("title"));
        String myBookTitle = ((String) delegateExecution.getVariable("myBookTitle"));

        this.setBookPaths(title, myBookTitle, delegateExecution);

        String editorsUsernameString = ((String) delegateExecution.getVariable("editors"));
        System.out.println(editorsUsernameString);

        ArrayList<String> editorsUsernameList = new ArrayList<String>();
        ArrayList<String> editorsThatDidntComment = new ArrayList<String>();

        String noSpaceStr = editorsUsernameString.replaceAll("\\s", "");
        int stringLength = noSpaceStr.length();
        String noBracketsStr = noSpaceStr.substring(1, stringLength - 1);
        System.out.println(noBracketsStr + "----------------");

        String[] editorsUsernamesStringList = noBracketsStr.split(",");

        for (String username : editorsUsernamesStringList) {
            editorsUsernameList.add(username);
        }

        for (String username : editorsUsernameList) {
            System.out.println(username + "milicaaaaa+++++");
        }

        delegateExecution.setVariable("editorsUsernameList", editorsUsernameList);
        delegateExecution.setVariable("editorsThatDidntComment", editorsThatDidntComment);
        delegateExecution.setVariable("numberOfEditorsThatDidntComment", editorsThatDidntComment.size());

    }

    public void setBookPaths(String title, String myBookTitle, DelegateExecution delegateExecution){

        BookPrototype bookPrototypePlagiat = bookPrototypeRepository.findByTitle(title);
        BookPrototype myBook = bookPrototypeRepository.findByTitle(myBookTitle);

        delegateExecution.setVariable("titlePath", bookPrototypePlagiat.getPath());
        delegateExecution.setVariable("myBookTitlePath", myBook.getPath());

    }

}
