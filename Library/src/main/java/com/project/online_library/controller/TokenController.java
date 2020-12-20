package com.project.online_library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.online_library.dto.OrderRequestDto;
import com.project.online_library.service.TokenService;


@CrossOrigin
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PutMapping()
    public ResponseEntity<String> makeToken(@RequestBody OrderRequestDto orderRequestDto)  {
        return new ResponseEntity<String>(tokenService.makeToken(orderRequestDto), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment() {
       // System.out.println(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
