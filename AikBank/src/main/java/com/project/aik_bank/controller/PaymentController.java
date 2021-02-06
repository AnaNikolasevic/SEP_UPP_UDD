package com.project.aik_bank.controller;


import com.project.aik_bank.dto.PayeerFormDTO;
import com.project.aik_bank.dto.PaymentRequestDTO;
import com.project.aik_bank.model.Payment;
import com.project.aik_bank.service.PaymentService;
import com.project.aik_bank.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.text.ParseException;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
public class PaymentController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PaymentService paymentService;

    @PostMapping(path= "/pay", consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment(@RequestHeader(value = "Authorization") String token) throws ValidationException, NoSuchElementException {

        System.out.println("tokeeeen");
        System.out.println(token);
        PaymentRequestDTO pr = jwtUtils.getPaymentRequest(token);
        Payment payment;
        try {
             payment = paymentService.create(pr);

        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        String url = "http://localhost:8083/aikBank/" + payment.getId();
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @PutMapping(path= "/pay", consumes = "application/json", produces = "application/json")
    public ResponseEntity pay(@RequestBody PayeerFormDTO payeerFormDTO) throws  NoSuchElementException {

        System.out.println("USAO SAM U KONTROLEEERR");

        String url = "";
        try{
            url = paymentService.pay(payeerFormDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("nema krosinika sa tim idem!", HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(url, HttpStatus.OK);
    }


}
