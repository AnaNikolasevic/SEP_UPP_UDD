package com.example.erste_bank.controller;


import com.example.erste_bank.dto.PCCRequestDTO;
import com.example.erste_bank.dto.ResponseDTO;
import com.example.erste_bank.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@CrossOrigin
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(path= "/pay", consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment(@RequestBody PCCRequestDTO pccRequestDTO) throws ParseException {

        ResponseDTO responseDTO = paymentService.pay(pccRequestDTO);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
