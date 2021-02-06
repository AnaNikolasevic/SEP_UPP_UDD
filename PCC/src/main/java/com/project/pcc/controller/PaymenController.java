package com.project.pcc.controller;

import com.project.pcc.dto.PCCRequestDTO;
import com.project.pcc.dto.ResponseDTO;
import com.project.pcc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin
@RestController
public class PaymenController {

    @Autowired
    PaymentService paymentService;


    @PostMapping(path= "/createPayment", consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment(@RequestBody PCCRequestDTO pccRequestDTO)  {

        ResponseDTO responseDTO = paymentService.createPayment(pccRequestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
