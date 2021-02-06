package com.project.online_library.camundaHendlers;

import com.project.online_library.model.BetaReader;
import com.project.online_library.model.Editor;
import com.project.online_library.model.Genre;
import com.project.online_library.repository.EditorRepository;
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
public class EditorsListener  implements TaskListener {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public void notify(DelegateTask delegateTask) {

        String mainEditor = (String) delegateTask.getVariable("mainEditor");

        TaskFormData tfd = delegateTask.getExecution().getProcessEngineServices().getFormService().
                getTaskFormData(delegateTask.getId());

        List<FormField> formFieldList = tfd.getFormFields();
        if(formFieldList!=null) {
            for (FormField field : formFieldList) {
                if (field.getId().equals("editors")) {
                    EnumFormType enumFormType = (EnumFormType) field.getType();
                    Map<String, String> values = enumFormType.getValues();
                    values.clear();

                    for (Editor editor : editorRepository.findAll()) {

                            if (!editor.getUsername().equals(mainEditor)) {
                                values.put(editor.getUsername(), editor.getUsername());
                                System.out.println(editor.getUsername());
                                break;
                            }

                    }
                }
            }
        }
    }
}
