package com.project.online_library.camundaHendlers;


import com.project.online_library.dto.FormFieldsDto;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenTaskHendler implements TaskListener {

    @Autowired
    IdentityService identityService;
    @Autowired
    FormService formService;

    public static FormFieldsDto formFieldsDto;

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("TRENUTNI TASK JEEE: " + delegateTask.getId());
        TaskFormData tfd = formService.getTaskFormData(delegateTask.getId());
        List<FormField> properties = tfd.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getId() + fp.getType());
        }
   //     formFieldsDto = new FormFieldsDto(delegateTask.getId(), delegateTask.getId(), properties);;
    }

}
