package com.project.online_library.controller;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
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
import org.springframework.web.client.RestTemplate;

import com.project.online_library.dto.FormFieldsDto;
import com.project.online_library.dto.FormSubmissionDto;

@CrossOrigin
@RestController
public class LibraryController {
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@GetMapping(path = "/registrationForm", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {
		//provera da li korisnik sa id-jem pera postoji
		//List<User> users = identityService.createUserQuery().userId("pera").list();
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("registration_process");
		ProcessInstance pi2 = runtimeService.startProcessInstanceByKey("plagiarismProcess");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	@GetMapping(path = "/genreBetaForm/{processInstanceId}", produces = "application/json")
    public @ResponseBody FormFieldsDto getGenreBetaForm(@PathVariable String processInstanceId) {

		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().taskName("Data Entry Beta").singleResult();
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDto(task.getId(), processInstanceId, properties);
    }
	
    @GetMapping("/libraryController")
    public ResponseEntity<String> appB(){

        final String uri = "http://secutiry-service:8082/securtyController";

        RestTemplate template = new RestTemplate();
       // String result =  restTemplate.getForObject(uri, ResponseEntity.class).toString();
        ResponseEntity<String> entity = template.getForEntity(uri, String.class);
        return entity;
    }

	@PostMapping(path = "/register/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		    // list all running/unsuspended instances of the process
//		    ProcessInstance processInstance =
//		        runtimeService.createProcessInstanceQuery()
//		            .processDefinitionKey("Process_1")
//		            .active() // we only want the unsuspended process instances
//		            .list().get(0);
		
//			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.getVariables(processInstanceId);
		if(dto.size()==1) {
	        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)runtimeService.getVariable(processInstanceId,"registration");
	        registration.add(dto.get(0));
	        runtimeService.setVariable(processInstanceId, "registration", registration);
		} else {
			runtimeService.setVariable(processInstanceId, "registration", dto);
		}
		formService.submitTaskForm(taskId, map);
        return new ResponseEntity<>(dto.get(dto.size()-1).getFieldValue(), HttpStatus.OK);
    }
	
	@GetMapping(path = "/uploadForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getUploadForm() {

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("mandatoryPublicationOfBooks");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	@PostMapping(path = "/uploadMandatoryBook/{username}/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity upload(@RequestBody List<FormSubmissionDto> dto, @PathVariable String username, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		FormSubmissionDto formSubmissionDto = new FormSubmissionDto("writerUsername", username);
		dto.add(formSubmissionDto);
	    runtimeService.setVariable(processInstanceId, "mandatoryBook", dto);
		formService.submitTaskForm(taskId, map);
        return new ResponseEntity<>(dto.get(dto.size()-1).getFieldValue(), HttpStatus.OK);
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
