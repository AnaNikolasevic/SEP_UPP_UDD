package com.project.online_library.controller;


import com.project.online_library.dto.FormFieldsDto;
import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.model.Editor;
import com.project.online_library.repository.EditorRepository;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
public class CamundaController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;

    @Autowired
    private EditorRepository editorRepository;

    @GetMapping("/form/{username}/{taskKey}")
    public ResponseEntity<List<FormFieldsDto>> getAllUserTasks(@PathVariable String username, @PathVariable String taskKey) {
        // return new ResponseEntity<List<BookPrototypeDTO>>(bookPrototypeService.getAllEditorsBookPrototypes(username), HttpStatus.OK);

        List<Task> tasks = taskService.createTaskQuery().taskAssigneeLike(username).list();
        System.out.println(tasks.size());

        List<FormFieldsDto> dtos = new ArrayList<FormFieldsDto>();

        for (Task task : tasks) {
            String s = task.getTaskDefinitionKey();
            if (s.equals(taskKey)) {
                System.out.println("TAAAASKKK KEEY:" + taskKey);
                String processInstanceId = task.getProcessInstanceId();
                TaskFormData tfd = formService.getTaskFormData(task.getId());
                List<FormField> properties = tfd.getFormFields();
                FormFieldsDto formFieldsDto = new FormFieldsDto(task.getId(), processInstanceId, properties);
                dtos.add(formFieldsDto);
                System.out.println(formFieldsDto.getFormFields());
            }

        }

        return new ResponseEntity<List<FormFieldsDto>>(dtos, HttpStatus.OK);
    }

    @GetMapping(path = "/getForm/{processId}", produces = "application/json")
    public @ResponseBody
    FormFieldsDto get(@PathVariable String processId) {

        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processId);
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getId() + fp.getType());
        }

        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }

    @PostMapping(path = "/subminForm/{taskId}/{variableName}", produces = "application/json")
    public @ResponseBody
    ResponseEntity crate(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId,  @PathVariable String variableName) {
        HashMap<String, Object> map = this.mapListToDto(dto);
        System.out.println(map.toString());
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, variableName, dto);
        formService.submitTaskForm(taskId, map);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for(FormSubmissionDto temp : list){
            System.out.println(temp.getId());
            System.out.println(temp.getFieldValue());
            map.put(temp.getId(), temp.getFieldValue());
        }

        return map;
    }



}