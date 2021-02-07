package com.project.online_library.controller;

import com.project.online_library.dto.BookPrototypeDTO;
import com.project.online_library.dto.FormFieldsDto;
import com.project.online_library.enums.BookStatus;
import com.project.online_library.model.Book;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.Editor;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.BookRepository;
import com.project.online_library.repository.EditorRepository;
import com.project.online_library.service.BookPrototypeService;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.TransitionInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.model.bpmn.instance.CallActivity;
import org.camunda.bpm.model.bpmn.instance.Event;
import org.camunda.bpm.tasklist.Tasklist;
import org.hibernate.event.service.internal.EventListenerServiceInitiator;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bookPrototype")
public class BookPrototypeController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAll() {

        return new ResponseEntity<List<Book>>( bookRepository.findAll(), HttpStatus.OK);
    }



}
