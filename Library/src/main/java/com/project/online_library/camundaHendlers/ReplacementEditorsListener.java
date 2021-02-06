package com.project.online_library.camundaHendlers;

import com.project.online_library.model.Editor;
import com.project.online_library.repository.EditorRepository;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReplacementEditorsListener implements TaskListener {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public void notify(DelegateTask delegateTask)   {

        ArrayList<String> editorsUsernameList = (ArrayList<String>) delegateTask.getVariable("editorsUsernameList");
        ArrayList<String> replacementEditorsList = (ArrayList<String>) delegateTask.getVariable("replacementEditorsList");

        TaskFormData tfd = delegateTask.getExecution().getProcessEngineServices().getFormService().
                getTaskFormData(delegateTask.getId());

        List<FormField> formFieldList = tfd.getFormFields();
        if (formFieldList != null) {
            for (FormField field : formFieldList) {
                if (field.getId().equals("replacementEditors")) {
                    EnumFormType enumFormType = (EnumFormType) field.getType();
                    Map<String, String> values = enumFormType.getValues();
                    values.clear();

                    boolean flag = false;
                    for (Editor editor : editorRepository.findAll()) {
                        for(String didntCommentUsername: editorsUsernameList){
                            if (editor.getUsername().equals(didntCommentUsername)) {
                                flag = true;
                            }
                        }
                        for(String didntCommentUsername: replacementEditorsList){
                            if (editor.getUsername().equals(didntCommentUsername)) {
                                flag = true;
                            }
                        }
                        if(!flag){
                            values.put(editor.getUsername(), editor.getUsername());
                        }
                    }
                }
            }
        }
    }
}
