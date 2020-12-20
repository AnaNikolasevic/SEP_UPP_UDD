package com.project.paypal.controller;

import com.project.paypal.dto.PaymentRequestDTO;
import com.project.paypal.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    JwtUtils tokenUtils;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment(@RequestHeader(value = "Authorization") String token) {

        System.out.println(token);


        PaymentRequestDTO pr = tokenUtils.getPaymentRequest(token);

        System.out.println(pr.getPrice());
        System.out.println(pr.getCurrency());
        System.out.println(pr.getSellerId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
