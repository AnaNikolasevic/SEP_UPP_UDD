package com.project.card_payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardPaymentController {


    @GetMapping("/cardController")
    public ResponseEntity<?> index() {
        String s = "Hello from Card Payment Service";
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
