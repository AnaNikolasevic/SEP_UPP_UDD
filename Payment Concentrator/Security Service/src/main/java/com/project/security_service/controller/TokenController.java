package com.project.security_service.controller;

import com.project.security_service.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping()
    public ResponseEntity<String> getToken()  {
        return new ResponseEntity<String>(tokenService.getToken(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment() {
       // System.out.println(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
