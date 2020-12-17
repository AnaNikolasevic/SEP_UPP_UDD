package com.project.online_library.controller;


import com.project.online_library.model.Users;
import com.project.online_library.model.VerificationToken;
import com.project.online_library.service.RegistrationService;
import com.project.online_library.service.VerificationTokenService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin
@RestController
public class AccountController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    VerificationTokenService verificationTokenService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;


    @PostMapping(path="/activation/{token}/{processID}")
    public ResponseEntity activation (@PathVariable String token, @PathVariable String processID ){

        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (verificationToken == null){
            return new ResponseEntity<>("Your verification token is invalid", HttpStatus.NOT_FOUND);
        } else {
            Users user = verificationToken.getUser();

            if(!user.isEnabled()){
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

                if (verificationToken.getExpiryDate().before(currentTimestamp)){
                    return new ResponseEntity<>("Your verification token has expired", HttpStatus.NOT_FOUND);
                } else{
                    registrationService.activateUser(user);
                    runtimeService.setVariable(processID, "verification", "true");
                    Task task = taskService.createTaskQuery().processInstanceId(processID).list().get(0);
                    taskService.complete(task.getId());
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Your account is already activated", HttpStatus.ALREADY_REPORTED);
            }
        }
    }

}
