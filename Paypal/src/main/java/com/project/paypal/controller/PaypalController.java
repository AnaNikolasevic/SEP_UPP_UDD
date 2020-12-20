package com.project.paypal.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.paypal.dto.PaymentRequestDTO;
import com.project.paypal.service.PaypalService;
import com.project.paypal.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PaypalController {

    @Autowired
    PaypalService payPalService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(path= "/pay", consumes = "application/json", produces = "application/json")
    public ResponseEntity createPayment(@RequestHeader(value = "Authorization") String token) throws Throwable{

        System.out.println(token);
        PaymentRequestDTO pr = jwtUtils.getPaymentRequest(token);
       try {
            Payment payment = payPalService.createPayment(pr);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(link.getHref(), HttpStatus.OK);
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("redirect:/", HttpStatus.OK);
    }

    @GetMapping("/success")
    public String  successfullPayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {

        System.out.println("usao");

        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "Payment via paypal successful!";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "Payment via paypal failed!";
    }
}
