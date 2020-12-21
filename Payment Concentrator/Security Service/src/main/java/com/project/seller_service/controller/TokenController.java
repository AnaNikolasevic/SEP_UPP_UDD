package com.project.seller_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.seller_service.dto.OrderRequestDto;
import com.project.seller_service.service.TokenService;

@CrossOrigin
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PutMapping("")
    public ResponseEntity<String> getToken(@RequestBody OrderRequestDto orderRequestDto)  {
        return new ResponseEntity<String>(tokenService.getToken(orderRequestDto), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment() {
       // System.out.println(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
