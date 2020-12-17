package com.project.security_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SecurityController {


    @GetMapping("/kpController")
    public ResponseEntity<?> index() {
        String s = "Hello from KP";
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping("/securtyController")
    public ResponseEntity<String> appB(){

        final String uri = "http://card-payment:8083/cardController";

        RestTemplate template = new RestTemplate();
        // String result =  restTemplate.getForObject(uri, ResponseEntity.class).toString();
        ResponseEntity<String> entity = template.getForEntity(uri, String.class);
        return entity;
    }

}