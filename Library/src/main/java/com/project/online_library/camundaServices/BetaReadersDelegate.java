package com.project.online_library.camundaServices;

import com.project.online_library.customTypes.MultipleEnumType;
import com.project.online_library.enums.WriterMembershipStatus;
import com.project.online_library.model.BetaReader;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.form.type.AbstractFormFieldType;
import org.glassfish.jersey.Beta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BetaReadersDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        String betaReadersUsernamesString = ((String) delegateExecution.getVariable("betaReaders"));
        System.out.println(betaReadersUsernamesString);

        ArrayList<String> betaReadersUsernameList = new ArrayList<String>();
        ArrayList<String> betaReadersThatCommented = new ArrayList<String>();
        ArrayList<String> punishedBetaReaders = new ArrayList<String>();
        ArrayList<String> comments = new ArrayList<String>();


        String noSpaceStr = betaReadersUsernamesString.replaceAll("\\s", "");
        int stringLength = noSpaceStr.length();
        String noBracketsStr = noSpaceStr.substring(1, stringLength-1);
        System.out.println(noBracketsStr + "----------------");

        String[] betaReadersUsernamesStringList = noBracketsStr.split(",");


        for(String username: betaReadersUsernamesStringList){
            betaReadersUsernameList.add(username);
        }

        for(String username: betaReadersUsernameList){
            System.out.println(username + "milicaaaaa+++++");
        }

        delegateExecution.setVariable("betaReadersUsernameList", betaReadersUsernameList);
        delegateExecution.setVariable("betaReadersThatCommented", betaReadersThatCommented);
        delegateExecution.setVariable("punishedBetaReaders", punishedBetaReaders);
        delegateExecution.setVariable("comments", comments);

    }
}
