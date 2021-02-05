package com.project.online_library.camundaHendlers;

import com.project.online_library.model.BetaReader;
import com.project.online_library.model.Genre;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        ArrayList<String> comments = (ArrayList<String>) delegateTask.getVariable("comments");

        TaskFormData tfd = delegateTask.getExecution().getProcessEngineServices().getFormService().
                getTaskFormData(delegateTask.getId());

        List<FormField> formFieldList = tfd.getFormFields();
        if(formFieldList!=null){
            for(FormField field : formFieldList){
                if( field.getId().equals("comments")) {
                    EnumFormType enumFormType = (EnumFormType) field.getType();
                    Map<String, String> values = enumFormType.getValues();
                    values.clear();

                    for (String comment : comments) {
                        values.put(comment, comment);
                        System.out.println(comment);
                    }
                }
            }
        }

    }
}
