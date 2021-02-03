package com.project.online_library.camundaServices;

import com.project.online_library.dto.FormSubmissionDto;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.form.type.AbstractFormFieldType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetaReadersDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        if( delegateExecution.getVariable("betaReaders") == null){
            System.out.println("Milica");
        }
    }
}
