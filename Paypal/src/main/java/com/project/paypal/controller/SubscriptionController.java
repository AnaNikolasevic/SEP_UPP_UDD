package com.project.paypal.controller;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.paypal.dto.PaymentRequestDTO;
import com.project.paypal.dto.SubscriptionRequestDTO;
import com.project.paypal.dto.SubscriptionResponseDTO;
import com.project.paypal.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

@CrossOrigin
@RestController
@RequestMapping("")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping("/createSubscription")
    public SubscriptionResponseDTO createSuscription(@RequestBody SubscriptionRequestDTO subsDTO) {
        return subscriptionService.createSubscription(subsDTO);
    }

    @PostMapping("/executeSubscription/{token}/{sellerId}")
    public String executeSubscription(@PathVariable("token") String token, @PathVariable("sellerId") long sellerId) {

        String status = subscriptionService.executeSubscription(token, sellerId);
        if(status.equals("error")) return "https://localhost:8672/paypal/errorSubscription.html";
        return "https://localhost:8672/paypal/successSubscription.html";

    }
}
