package com.project.online_library.controller;


import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.dto.LoginDto;
import com.project.online_library.model.Users;
import com.project.online_library.model.VerificationToken;
import com.project.online_library.service.UserService;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path="/login")
    public ResponseEntity login (@RequestBody LoginDto user ){

        Users users = userService.login(user);

        if (users == null){
            return new ResponseEntity<>("null", HttpStatus.NOT_FOUND);
        }
        if (users.getPassword() != users.getPassword()){
            return new ResponseEntity<>("Incorect password!", HttpStatus.NOT_FOUND);
        }
        if (users.isEnabled() == false){
            return new ResponseEntity<>("You have not confirmed registration", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);

    }



}
