package com.project.online_library.camundaHendlers;

import com.project.online_library.model.BetaReader;
import com.project.online_library.model.Genre;
import com.project.online_library.repository.BetaReaderRepository;
import com.project.online_library.repository.GenreRepository;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BetaReaderListener implements TaskListener {

    @Autowired
    BetaReaderRepository betaReaderRepository;

    @Autowired
    FormService formService;

    @Autowired
    TaskService taskService;

    @Override
    public void notify(DelegateTask delegateTask) {


        String genre = (String) delegateTask.getVariable("genre");

        TaskFormData tfd = delegateTask.getExecution().getProcessEngineServices().getFormService().
                getTaskFormData(delegateTask.getId());

        List<FormField> formFieldList = tfd.getFormFields();
        if(formFieldList!=null){
            for(FormField field : formFieldList){
                if( field.getId().equals("betaReaders")) {
                    EnumFormType enumFormType = (EnumFormType) field.getType();
                    Map<String, String> values = enumFormType.getValues();
                    for (BetaReader betaReader : betaReaderRepository.findAll()) {
                        for(Genre g : betaReader.getGenreBetaList()) {
                            if(g.getName().equals(genre)) {
                                System.out.println("uso1+++++++++++++++");
                                values.put(betaReader.getUsername(), betaReader.getUsername());
                                System.out.println(betaReader.getUsername());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
